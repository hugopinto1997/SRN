
package com.hugopinto.srn.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hugopinto.srn.R;
import com.hugopinto.srn.Datos.Persona;
import com.hugopinto.srn.DBHelper.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Agregar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Agregar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Agregar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText carn, not, mat, cat;
    private Button btn;





    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Agregar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Agregar.
     */
    // TODO: Rename and change types and number of parameters
    public static Agregar newInstance(String param1, String param2) {
        Agregar fragment = new Agregar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agregar, container, false);
        DBHelper.getInstance(v.getContext());
        carn = v.findViewById(R.id.carnet);
        btn = v.findViewById(R.id.button);
        mat = v.findViewById(R.id.materia);
        not = v.findViewById(R.id.nota);
        cat = v.findViewById(R.id.catedratico);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (carn.getText().toString().isEmpty() ||
                        mat.getText().toString().isEmpty() ||
                        not.getText().toString().isEmpty() ||
                        cat.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(), "Llene loc campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (Float.parseFloat(not.getText().toString()) > 10) {
                        Toast.makeText(v.getContext(), "Nota mayor a 10, ingrese una nota menor o igual a 10", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean flag = DBHelper.myDB.add(new Persona(carn.getText().toString(), not.getText().toString(), mat.getText().toString(), cat.getText().toString()));
                        if (flag) {
                            Log.d("Edit", not.getText().toString());
                        }
                    }
                }
            }
        });
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
