import java.util.Scanner;

public class Parqueaderocali {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] bajoCilindraje = new String[20]; // 20 puestos
        String[] altoCilindraje = new String[10]; // 10 puestos
        int[] minutosIngresoBajo = new int[20];
        int[] minutosIngresoAlto = new int[10];
        int opcion;

        do {
            System.out.println("\nMenú opciones:");
            System.out.println("1. Registrar moto");
            System.out.println("2. Cobrar tarifa");
            System.out.println("3. Visualizar estado parqueadero");
            System.out.println("4. Abandonar menú");
            System.out.print("Digite una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            if (opcion == 1) {
                System.out.println("Digite la placa de la moto:");
                String placa = sc.next();
                System.out.println("Digite el cilindraje de la moto:");
                int cilindraje = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                System.out.println("Digite la hora de ingreso en formato HH:MM (ejemplo 14:30):");
                String[] tiempo = sc.nextLine().split(":");
                int minutosIngreso = Integer.parseInt(tiempo[0]) * 60 + Integer.parseInt(tiempo[1]);

                if (cilindraje < 400) {
                    System.out.println("Digite la posición (1-20):");
                    int pos = sc.nextInt() - 1;
                    if (pos >= 0 && pos < 20 && bajoCilindraje[pos] == null) {
                        bajoCilindraje[pos] = placa;
                        minutosIngresoBajo[pos] = minutosIngreso;
                        System.out.println("Moto registrada en puesto " + (pos + 1));
                    } else {
                        System.out.println("Posición ocupada o inválida.");
                    }
                } else {
                    System.out.println("Digite la posición (1-10):");
                    int pos = sc.nextInt() - 1;
                    if (pos >= 0 && pos < 10 && altoCilindraje[pos] == null) {
                        altoCilindraje[pos] = placa;
                        minutosIngresoAlto[pos] = minutosIngreso;
                        System.out.println("Moto registrada en puesto " + (pos + 1));
                    } else {
                        System.out.println("Posición ocupada o inválida.");
                    }
                }
            } else if (opcion == 2) {
                System.out.println("Digite la placa de la moto:");
                String placa = sc.next();
                sc.nextLine(); // Limpiar buffer
                System.out.println("Digite la hora de salida en formato HH:MM (ejemplo 15:45):");
                String[] tiempo = sc.nextLine().split(":");
                int minutosSalida = Integer.parseInt(tiempo[0]) * 60 + Integer.parseInt(tiempo[1]);
                boolean encontrada = false;

                for (int i = 0; i < 20; i++) {
                    if (bajoCilindraje[i] != null && bajoCilindraje[i].equals(placa)) {
                        int tiempoEstacionado = minutosSalida - minutosIngresoBajo[i];
                        int costo = (tiempoEstacionado <= 30) ? 0 : (tiempoEstacionado <= 60) ? 800 : 2000;
                        System.out.println("Tiempo: " + tiempoEstacionado + " minutos. Costo: $" + costo);
                        bajoCilindraje[i] = null;
                        encontrada = true;
                        break;
                    }
                }

                for (int i = 0; i < 10; i++) {
                    if (altoCilindraje[i] != null && altoCilindraje[i].equals(placa)) {
                        int tiempoEstacionado = minutosSalida - minutosIngresoAlto[i];
                        int costo = (tiempoEstacionado <= 30) ? 0 : (tiempoEstacionado <= 60) ? 800 : 2000;
                        System.out.println("Tiempo: " + tiempoEstacionado + " minutos. Costo: $" + costo);
                        altoCilindraje[i] = null;
                        encontrada = true;
                        break;
                    }
                }

                if (!encontrada) {
                    System.out.println("Moto no encontrada.");
                }
            } else if (opcion == 3) {
                System.out.println("Puestos de bajo cilindraje:");
                for (int i = 0; i < 20; i++) {
                    System.out.println("Puesto " + (i + 1) + ": " + (bajoCilindraje[i] == null ? "Libre" : "Ocupado"));
                }
                System.out.println("Puestos de alto cilindraje:");
                for (int i = 0; i < 10; i++) {
                    System.out.println("Puesto " + (i + 1) + ": " + (altoCilindraje[i] == null ? "Libre" : "Ocupado"));
                }
            }

        } while (opcion != 4);

        System.out.println("Abandonando programa!...");
        sc.close();
    }
}