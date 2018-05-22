package com.hugopinto.srn.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hugopinto.srn.DBHelper.DBHelper;
import com.hugopinto.srn.Datos.Persona;
import com.hugopinto.srn.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Actualizar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Actualizar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actualizar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private EditText identificador, nt, sv;
    private String pfs, assgnmt;
    private Button bbuscar, bactualizar;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Actualizar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Actualizar.
     */
    // TODO: Rename and change types and number of parameters
    public static Actualizar newInstance(String param1, String param2) {
        Actualizar fragment = new Actualizar();
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

        View v =inflater.inflate(R.layout.fragment_actualizar, container, false);

        identificador = v.findViewById(R.id.jalarcarneta);
        nt = v.findViewById(R.id.notaa);
        bbuscar = v.findViewById(R.id.buscarida);
        bactualizar = v.findViewById(R.id.buttona);

        bbuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Persona P = DBHelper.myDB.findUser(identificador.getText().toString());
                assgnmt= P.getMateria().toString();
                pfs = P.getCatedratico().toString();
                if(P==null){
                    Toast.makeText(v.getContext(),"El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
                    nt.setText("");
                }
                else{
                    nt.setText(P.getNota());
                }
            }
        });
        bactualizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (nt.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(), "No se ha buscado ningun dato", Toast.LENGTH_SHORT).show();
                } else {
                    if (Float.parseFloat(nt.getText().toString()) > 10) {
                        Toast.makeText(v.getContext(), "Nota mayor a 10, ingrese una nota menor o igual a 10", Toast.LENGTH_SHORT).show();
                    } else {
                        DBHelper.myDB.editUser(new Persona(identificador.getText().toString(), nt.getText().toString(), assgnmt, pfs));
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
