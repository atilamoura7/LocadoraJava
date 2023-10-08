package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Conexao
{
    private static final String URL = "jdbc:mysql://localhost:3306/locacao";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private static Connection conn = null;
    
    public static Connection getConexao()
    {
        try
        {
            if(conn==null)
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                return conn;
            }
            else
            {
                return conn;
            }
        }
        catch(SQLException e)
        {
               JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer conexão, exception: "+e,"Erro de conexão", JOptionPane.ERROR_MESSAGE);
               return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
