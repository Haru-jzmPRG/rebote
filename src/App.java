public class App {
    public static final String CLEAN_SCREEN = "\033[H\033[2J";
    public static final String FONDO_ROJO = "\u001B[41m";
    public static final String FONDO_VERDE = "\u001B[42m";
    public static final String FONDO_AMARILLO = "\u001B[43m";
    public static final String FONDO_AZUL = "\u001B[44m";
    public static void main(String[] args) throws Exception {
        // Pedir dimensiones del área de juego
        int ancho = Integer.parseInt(System.console().readLine("Introduce el ancho: "));
        int alto = Integer.parseInt(System.console().readLine("Introduce el alto: "));
        String caracter = "©";
        //Primera posición del caracter
        int x = ancho / 2;
        int y = alto / 2;
        //Velocidades iniciales
        int vx = 0;
        int vy = 0;
        //Definimos si el movimiento es automático o manual
        boolean automatico = false;
        //Condición de salida
        boolean salir = false;
        do {
            //Bucle para pintar el rectangulo y el caracter
            for (int i = 1; i <= alto; i++) {
                for (int j = 1; j <= ancho; j++) {
                    if (i == 1 || i == alto || j == 1 || j == ancho) {
                        System.out.print("*");
                    } else if (i == y && j == x) {
                        System.out.print(caracter);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            //Si no es automático, pedimos dirección
            if (!automatico) {
                System.out.println("W. Arriba");
                System.out.println("A. Izquierda");
                System.out.println("S. Abajo");
                System.out.println("D. Derecha");
                System.out.println("X. Automatico");
                System.out.println("SALIR");

                //Leemos opción, charAt(0) lo que hace es leer la primera letra de la cadena, funciona con indices
                String opcion = "" + System.console().readLine().toUpperCase().charAt(0);

                switch (opcion) {
                    case "W":
                        //Si va hacia arriba no saldra y se quedara justo debajo del borde
                        y = Math.max(2, y - 1);
                        caracter = FONDO_AZUL + "©" + "\u001B[0m";
                        break;
                    case "A":
                        //Si va hacia la izquierda no saldra y se quedara justo en el borde
                        x = Math.max(2, x - 1);
                        caracter = FONDO_ROJO + "©" + "\u001B[0m";
                        break;
                    case "S":
                        //Si va hacia la abajo no saldra y se quedara justo en el borde
                        y = Math.min(alto - 1, y + 1);
                        caracter = FONDO_VERDE + "©" + "\u001B[0m";
                        break;
                    case "D":
                        //Si va hacia la derecha no saldra y se quedara justo en el borde
                        x = Math.min(ancho - 1, x + 1);
                        caracter = FONDO_AMARILLO + "©" + "\u001B[0m";
                        break;
                    case "X":
                        //Generamos velocidades aleatorias entre -2 y 2, excluyendo el 0
                        do {
                            //Genera un número entre 1 y 2, y lo multiplica por -1, 0 o 1
                            vx = ((int) (Math.random() * 2) + 1) * ((int) (Math.random() * 3) - 1);
                            vy = ((int) (Math.random() * 2) + 1) * ((int) (Math.random() * 3) - 1);
                        } while (vx == 0 || vy == 0);
                        automatico = true;
                        break;
                    default:
                        salir = true;
                        break;
                }
            } else {
                //Se le suma la velocidad a la posición
                x += vx;
                y += vy;
                
                //Si choca con un borde, rebota invirtiendo la velocidad en ese eje
                if((x<=1 && vx<0) || (x>=ancho && vx>0)){
                    if (vx<0) {
                        x=2;
                    }else {
                        x=ancho-1;
                    }
                    vx*=-1;
                }
                if((y<=1 && vy<0) || (y>=alto && vy>0)){
                    if (vy<0) {
                        y=2;
                    }else {
                        y=ancho-1;
                    }
                    vy*=-1;
                }
            }

            Thread.sleep(100);
            System.out.print(CLEAN_SCREEN);
        } while (!salir);

    }
}
