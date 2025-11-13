public class App {
    public static void main(String[] args) throws Exception {
        int ancho = Integer.parseInt(System.console().readLine("Introduce el ancho: "));
        int alto = Integer.parseInt(System.console().readLine("Introduce el alto: "));
        int x = ancho / 2;
        int y = alto / 2;

        boolean salir = false;
        do {
            for (int i = 1; i <= alto; i++) {
                for (int j = 1; j <= ancho; j++) {
                    if (i == 1 || i == alto || j == 1 || j == ancho) {
                        System.out.print("*");
                    } else if (i == y && j == x) {
                        System.out.print("M");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

            System.out.println("W. Arriba");
            System.out.println("A. Izquierda");
            System.out.println("S. Abajo");
            System.out.println("D. Derecha");
            System.out.println("SALIR");

            String opcion = "" + System.console().readLine().toUpperCase().charAt(0);

            switch (opcion) {
                case "W":
                    y = y-1;
                    break;
                case "A":
                    x = x-1;
                    break;
                case "S":
                    y = y+1;
                    break;
                case "D":
                    x = x+1;
                default:
                    break;
            }
        } while (!salir);

    }
}
