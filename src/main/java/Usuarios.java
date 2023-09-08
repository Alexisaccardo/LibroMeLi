import java.sql.*;
import java.util.Scanner;

public class Usuarios {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*****BIENVENIDOS*****");

        System.out.println("¿Deseas registrar tu opinion o consultar opinon de otros usuarios?: ");
        String respuesta = scanner.nextLine();

        int valoropinion = 0;

        if (respuesta.equals("registrar opinion")) {
            //

            System.out.print("Ingrese su numero de documento: ");
            String Documento = scanner.nextLine();

            System.out.print("Ingrese su opinion: ");
            String Opinion = scanner.nextLine();

            System.out.print("Ingrese su expresion, si estas de acuerdo con soltar o no: ");
            String Expresion = scanner.nextLine();

            if (Expresion.equals("de acuerdo")){
                valoropinion = 1;
                Insertacuerdo(Documento, Opinion, valoropinion); //
            }else{
                valoropinion = 1;
                Insert(Documento, Opinion, valoropinion); //
            }

            if (Documento.equals("") || Opinion.equals("") || Expresion.equals("")) {
                System.out.println("No se admiten datos vacios.");
            }
        }
        if (respuesta.equals("consultar opinion")){
            int acuerdo = Selectacuerdo();
            System.out.println("Esta es la cantidad de personas que estan de acuerdo: "+ acuerdo);

            int desacuerdo = Selectdesacuerdo();
            System.out.println("Esta es la cantidad de personas que estan en desacuerdo: "+ desacuerdo);
    }

    }

    private static int Selectdesacuerdo() throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/libromeli";
        String username2 = "root";
        String pass2 = "";

        int total = 0;

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM usuarios WHERE Desacuerdo = 1");

        while(resultSet2.next()){
            String Documento = resultSet2.getString("Documento");
            String Opinion = resultSet2.getString("Opinion");
            int Desacuerdo = resultSet2.getInt("Desacuerdo");

            total = total + Desacuerdo;

            System.out.println("este es el documento: " + Documento +  " Opinion: "+ Opinion);

        }
        return total;
    }

    private static void Insertacuerdo(String documento, String opinion, int de_acuerdo) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/libromeli";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");


            // Sentencia INSERT
            String sql = "INSERT INTO usuarios (documento, opinion, de_acuerdo, desacuerdo) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, documento);
            preparedStatement.setString(2, opinion);
            preparedStatement.setInt(3, de_acuerdo);
            preparedStatement.setInt(4, 0);

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("opinion agregada exitosamente.");
            } else {
                System.out.println("No se pudo agregar su opinion.");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void Insert(String documento, String opinion, int desacuerdo) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/libromeli";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");


            // Sentencia INSERT
            String sql = "INSERT INTO usuarios (documento, opinion, de_acuerdo, desacuerdo) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, documento);
            preparedStatement.setString(2, opinion);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, desacuerdo);

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("opinion agregada exitosamente.");
            } else {
                System.out.println("No se pudo agregar su opinion.");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int Selectacuerdo() throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/libromeli";
        String username2 = "root";
        String pass2 = "";

        int total = 0;

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM usuarios WHERE De_acuerdo = 1");

        while(resultSet2.next()){
            String Documento = resultSet2.getString("Documento");
            String Opinion = resultSet2.getString("Opinion");
            int De_acuerdo = resultSet2.getInt("De_acuerdo");

            total = total + De_acuerdo;

            System.out.println("este es el documento: " + Documento +  " Opinion: "+ Opinion);
        }
        return total;
    }
    }
