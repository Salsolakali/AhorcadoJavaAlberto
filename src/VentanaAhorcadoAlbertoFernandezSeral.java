
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alberto Fernandez
 * Este programa es el juego del ahorcado version Pablo Escobar. El jugador tiene que averiguar una
 * palabra oculta antes de que quiebren al ahorcado
 * 
 */
public class VentanaAhorcadoAlbertoFernandezSeral extends javax.swing.JFrame {
    String [] listaPalabras;
    String palabraOculta = "GONORREA";
    int numeroDeFallos = 0;
    boolean finalPartida = false;

    /**
     * Creates new form VentanaAhorcadoAlbertoFernandezSeral
     */
    public VentanaAhorcadoAlbertoFernandezSeral() {
        initComponents();
        eligePalabraOculta();
        dibujaImagen (0);
        ponGuiones();            
    }
   
    private void eligePalabraOculta(){
        //Este metodo elige una palabra oculta de una lista de palabras
        
        //Inicializo las palabras ocultas que guardare en el array
        listaPalabras = new String [] {"HIJUEPUTA", "GONORREA", "MALPARIO","PATRON", "PAPA", "NARCO", "SAPO", "TOMBO"};
        
        //Elijo una de ls palabras del array de manera aleatoria
        Random aleatorio = new Random();
        int posicionElegida = aleatorio.nextInt(listaPalabras.length);
        
        palabraOculta = listaPalabras [posicionElegida];
    }
    private void ponGuiones(){
        //Este metodo crea un nuevo string con tantos guiones bajos y estpacios como letras tenga la palabra ocualta a adivinar
        String palabraConGuiones = "";
        
        //Este bucle lee el numero de caracteres de la palabra oculta y los cambia por guiones
        for(int i = 0; i <palabraOculta.length(); i++){
        
        palabraConGuiones = palabraConGuiones + "_ ";
        }
        //Posiciono los guiones en la ventana
        ventana.setText(palabraConGuiones);
    }
    
    private void pasaLetra (JButton boton){
        //Este metodo desactiva el boton que he pulsado y le pasa a compruebaLetra la letra que hay en el boton que he pulsado
        //Siempre que no haya acabado la partida, hare el bucle del juego (comprobar la letra y cambiar la imagen)
        if (finalPartida == false){
            //Deshabilito el boton
            boton.setEnabled(false);
            //Extraigo la letra del boton
            chequeaLetra(boton.getText());
        }
        
        //Si he acabado la partida, activo los botones y voy a jugar otra partida
        else{
            boton.setEnabled(true);
            otraPartida(boton);
        }
    }
    private void otraPartida (JButton boton){
        //Este método me pregunta si quiero jugar otra partida.
        //Activo los botones de si y no
        SI.setEnabled(true);
        NO.setEnabled(true);
        
        //Recojo el texto del yes/no button y lo guardo en la variable otra
        String otra;
        otra = boton.getText();
        ventana.setText("¿OTRA PARTIDA?");
        
        //Si he pulsado que si, entonces iniciaré una nueva partida
        if ("SI".equals(otra)){
            //Pongo el contador de fallos a 0
           numeroDeFallos = 0;
            
           //Reinicio los 3 metodos que me inicializan el juego
            eligePalabraOculta();
            ponGuiones();
            dibujaImagen (0);
            
            //Inicio el metodo que me pone todos los botones en su posicion inicial
            reiniciaLosBotones();
            
            //Reinicio la variable que controla el final de la partida
            finalPartida = false;
            
        }
        else {
            //Si le ha dado a que no, finalizo
            
            finalPartida = true;
            
        }
    }
    private void reiniciaLosBotones(){
        //Este metodo llama pone todos los botones en su posicion incial
        SI.setEnabled(false);
        NO.setEnabled(false);
        A.setEnabled(true);
        B.setEnabled(true);
        C.setEnabled(true);
        D.setEnabled(true);
        E.setEnabled(true);
        F.setEnabled(true);
        G.setEnabled(true);
        H.setEnabled(true);
        I.setEnabled(true);
        J.setEnabled(true);
        K.setEnabled(true);
        L.setEnabled(true);
        M.setEnabled(true);
        N.setEnabled(true);
        Ñ.setEnabled(true);
        O.setEnabled(true);
        P.setEnabled(true);
        Q.setEnabled(true);
        R.setEnabled(true);
        S.setEnabled(true);
        T.setEnabled(true);
        U.setEnabled(true);
        V.setEnabled(true);
        W.setEnabled(true);
        X.setEnabled(true);
        Y.setEnabled(true);
        Z.setEnabled(true);
    }
    private void chequeaLetra(String letra){
        //Este metodo comprueba que la letra que le paso del boton está dentro del String que compone la palabra oculta
       
        //La convierto en mmayuscula por si fuera minúscula
        letra = letra.toUpperCase();
        
        //Cojo el string de la pantalla en el estado en el que este 
        StringBuilder palabraConGuiones = new StringBuilder(ventana.getText());
        
        //Comparo si tengo la letra que he pulsado en la palabra oculta
        if(palabraOculta.contains(letra)){
            //Voy recorriendo todos los caracteres de la palabra oculta
            for(int i = 0; i < palabraOculta.length(); i++){
                
                //Si coincide, pongo la letra en lugar del guion. Letra.charAt tiene 0 porque es un string compuesto por una unica letra
                if(palabraOculta.charAt(i) == letra.charAt(0)){
                    
                    //Cambio la posicion i y le meto la letra en la posicion i
                    palabraConGuiones.setCharAt(2*i, letra.charAt(0));
                 }
            }
        }
       
        //Si no tiene la letra pulsada, voy al metodo que me dibuja al ahorcado ahorcandose
        else{
            numeroDeFallos ++;
            dibujaImagen(numeroDeFallos);
        }
        //Pongo el nuevo String con la nueva letra
        ventana.setText(palabraConGuiones.toString());
        
        //Compruebo por si he ganado la partida. En este caso será si no hay guiones en el texto de la pantalla
        if (!ventana.getText().contains("_") && finalPartida == false){
            dibujaImagen(-1);
            ventana.setText("¡GANASTE! ¡BRAVO POR ESE "+palabraOculta+"!");
            finalPartida = true;
            
        }
        //Si ya tengo 6 fallos, la partida ha terminado
        if(numeroDeFallos == 6 && finalPartida == false){
            ventana.setText("¡LOSER! LA PALABRA CORRECTA ERA "+palabraOculta);
            finalPartida = true;
        }
        
    }
    private void dibujaImagen (int numeroDeFallos){
        //Este metodo va cambiando la imagen que va ahorcando al hombre
        //Lo paso como imagen, en principio va nulo
        ImageIcon horca = null;
        //Ruta de la imagen que irá cambiando
        URL nombreImagen = null;
        //Creo dos variables de alto y ancho que valdrán lo que el ancho y el alto de la pantalla
        int ancho = marco.getWidth();
        int alto = marco.getHeight();

        //Cambio la ruta de la foto en funcion de los fallos
        switch(numeroDeFallos){
            case 0: nombreImagen = getClass().getResource("/Imagenes/ahorcado_0.png");break;
            case 1: nombreImagen = getClass().getResource("/Imagenes/ahorcado_1.png");break;
            case 2: nombreImagen = getClass().getResource("/Imagenes/ahorcado_2.png");break;
            case 3: nombreImagen = getClass().getResource("/Imagenes/ahorcado_3.png");break;
            case 4: nombreImagen = getClass().getResource("/Imagenes/ahorcado_4.png");break;
            case 5: nombreImagen = getClass().getResource("/Imagenes/ahorcado_5.png");break;
            case -1: nombreImagen = getClass().getResource("/Imagenes/acertasteTodo.png");break;
            default: nombreImagen = getClass().getResource("/Imagenes/ahorcado_fin.png");
            }
        // el primer image icon (el de dentro) pasa de imagen a icono y el segundo (el de fuera) lo vuelve a convertir de icono a imagen
        horca = new ImageIcon(new ImageIcon(nombreImagen).getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        //Pongo esta nueva imagen en el marco
        marco.setIcon(horca);
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventana = new javax.swing.JLabel();
        marco = new javax.swing.JLabel();
        A = new javax.swing.JButton();
        B = new javax.swing.JButton();
        C = new javax.swing.JButton();
        D = new javax.swing.JButton();
        E = new javax.swing.JButton();
        F = new javax.swing.JButton();
        G = new javax.swing.JButton();
        H = new javax.swing.JButton();
        I = new javax.swing.JButton();
        J = new javax.swing.JButton();
        K = new javax.swing.JButton();
        L = new javax.swing.JButton();
        M = new javax.swing.JButton();
        N = new javax.swing.JButton();
        Ñ = new javax.swing.JButton();
        O = new javax.swing.JButton();
        P = new javax.swing.JButton();
        Q = new javax.swing.JButton();
        R = new javax.swing.JButton();
        S = new javax.swing.JButton();
        T = new javax.swing.JButton();
        U = new javax.swing.JButton();
        V = new javax.swing.JButton();
        W = new javax.swing.JButton();
        X = new javax.swing.JButton();
        Y = new javax.swing.JButton();
        Z = new javax.swing.JButton();
        botonVacio = new javax.swing.JButton();
        SI = new javax.swing.JButton();
        NO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ventana.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ventana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventana.setText("_ _ _ _ _");

        A.setText("A");
        A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AMousePressed(evt);
            }
        });

        B.setText("B");
        B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BMousePressed(evt);
            }
        });

        C.setText("C");
        C.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CMousePressed(evt);
            }
        });

        D.setText("D");
        D.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DMousePressed(evt);
            }
        });

        E.setText("E");
        E.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EMousePressed(evt);
            }
        });

        F.setText("F");
        F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FMousePressed(evt);
            }
        });
        F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FActionPerformed(evt);
            }
        });

        G.setText("G");
        G.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GMousePressed(evt);
            }
        });

        H.setText("H");
        H.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HMousePressed(evt);
            }
        });

        I.setText("I");
        I.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                IMousePressed(evt);
            }
        });

        J.setText("J");
        J.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JMousePressed(evt);
            }
        });

        K.setText("K");
        K.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                KMousePressed(evt);
            }
        });

        L.setText("L");
        L.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LMousePressed(evt);
            }
        });

        M.setText("M");
        M.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MMousePressed(evt);
            }
        });
        M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MActionPerformed(evt);
            }
        });

        N.setText("N");
        N.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NMousePressed(evt);
            }
        });

        Ñ.setText("Ñ");
        Ñ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ÑMousePressed(evt);
            }
        });

        O.setText("O");
        O.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OMousePressed(evt);
            }
        });

        P.setText("P");
        P.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PMousePressed(evt);
            }
        });

        Q.setText("Q");
        Q.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                QMousePressed(evt);
            }
        });

        R.setText("R");
        R.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RMousePressed(evt);
            }
        });

        S.setText("S");
        S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SMousePressed(evt);
            }
        });
        S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SActionPerformed(evt);
            }
        });

        T.setText("T");
        T.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TMousePressed(evt);
            }
        });

        U.setText("U");
        U.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UMousePressed(evt);
            }
        });

        V.setText("V");
        V.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                VMousePressed(evt);
            }
        });

        W.setText("W");
        W.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                WMousePressed(evt);
            }
        });

        X.setText("X");
        X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                XMousePressed(evt);
            }
        });

        Y.setText("Y");
        Y.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                YMousePressed(evt);
            }
        });

        Z.setText("Z");
        Z.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ZMousePressed(evt);
            }
        });
        Z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZActionPerformed(evt);
            }
        });

        botonVacio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonVacioMousePressed(evt);
            }
        });

        SI.setText("SI");
        SI.setEnabled(false);
        SI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SIMousePressed(evt);
            }
        });

        NO.setText("NO");
        NO.setEnabled(false);
        NO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NOMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ventana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(marco, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(G, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(J, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Ñ, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(SI)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(U, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(V, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(X, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Y, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Z, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonVacio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(NO))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ventana, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(marco, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ñ, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(U, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Y, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Z, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonVacio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SI)
                    .addComponent(NO))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Cada vez que presione un botón, le paso toda su informacion por referencia al metodo pasaLetra, el cual extraerá el texto del boton
    private void AMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AMousePressed
        // TODO add your handling code here:
        pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_AMousePressed

    private void BMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BMousePressed
        // TODO add your handling code here:

        pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_BMousePressed

    private void CMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMousePressed
        // TODO add your handling code here:
        pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_CMousePressed

    private void DMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_DMousePressed

    private void GMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_GMousePressed

    private void EMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_EMousePressed

    private void FMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_FMousePressed

    private void FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FActionPerformed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_FActionPerformed

    private void JMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_JMousePressed

    private void KMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_KMousePressed

    private void NMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_NMousePressed

    private void LMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_LMousePressed

    private void MMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_MMousePressed

    private void MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MActionPerformed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_MActionPerformed

    private void HMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_HMousePressed

    private void IMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_IMousePressed

    private void YMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_YMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_YMousePressed

    private void OMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_OMousePressed

    private void ZMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ZMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_ZMousePressed

    private void ZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZActionPerformed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_ZActionPerformed

    private void PMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_PMousePressed

    private void UMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_UMousePressed

    private void QMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_QMousePressed

    private void VMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_VMousePressed

    private void TMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_TMousePressed

    private void RMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_RMousePressed

    private void SMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_SMousePressed

    private void SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SActionPerformed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_SActionPerformed

    private void WMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_WMousePressed

    private void XMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMousePressed
        // TODO add your handling code here:
         pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_XMousePressed

    private void botonVacioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonVacioMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_botonVacioMousePressed

    private void ÑMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ÑMousePressed
        // TODO add your handling code here:
        pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_ÑMousePressed

    private void SIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SIMousePressed
        // TODO add your handling code here:
        pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_SIMousePressed

    private void NOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NOMousePressed
        // TODO add your handling code here:
        pasaLetra ((JButton) evt.getSource());
    }//GEN-LAST:event_NOMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAhorcadoAlbertoFernandezSeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAhorcadoAlbertoFernandezSeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAhorcadoAlbertoFernandezSeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAhorcadoAlbertoFernandezSeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAhorcadoAlbertoFernandezSeral().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A;
    private javax.swing.JButton B;
    private javax.swing.JButton C;
    private javax.swing.JButton D;
    private javax.swing.JButton E;
    private javax.swing.JButton F;
    private javax.swing.JButton G;
    private javax.swing.JButton H;
    private javax.swing.JButton I;
    private javax.swing.JButton J;
    private javax.swing.JButton K;
    private javax.swing.JButton L;
    private javax.swing.JButton M;
    private javax.swing.JButton N;
    private javax.swing.JButton NO;
    private javax.swing.JButton O;
    private javax.swing.JButton P;
    private javax.swing.JButton Q;
    private javax.swing.JButton R;
    private javax.swing.JButton S;
    private javax.swing.JButton SI;
    private javax.swing.JButton T;
    private javax.swing.JButton U;
    private javax.swing.JButton V;
    private javax.swing.JButton W;
    private javax.swing.JButton X;
    private javax.swing.JButton Y;
    private javax.swing.JButton Z;
    private javax.swing.JButton botonVacio;
    private javax.swing.JLabel marco;
    private javax.swing.JLabel ventana;
    private javax.swing.JButton Ñ;
    // End of variables declaration//GEN-END:variables
}
