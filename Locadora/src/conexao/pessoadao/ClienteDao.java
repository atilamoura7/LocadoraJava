package conexao.pessoadao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import pessoamodel.Cliente;

public class ClienteDao
{  
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static ArrayList<Cliente> listaClientes;
    
    public static void cadastrarCliente(Cliente cliente)
    {
        String sql = "INSERT INTO CLIENTE(NOME, DATA_NASCIMENTO, CPF, TELEFONE) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,cliente.getNome());
            ps.setDate(2,   Date.valueOf(cliente.getDataNascimento()));
            ps.setString(3, cliente.getCpf());
            ps.setString(4,cliente.getTelefone());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos", "CADASTRO REAIZADO COM SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel enviar os dados para o Banco de Dados, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
                if(ps!=null)
                {
                    ps.close();
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Não foi possivel encerrar os serviços, exepction: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    public static void ListaClientes()
    {
        String sql = "SELECT * FROM CLIENTE";
        listaClientes = new ArrayList<Cliente>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
              ps = Conexao.getConexao().prepareStatement(sql);
              rs = ps.executeQuery();
              while(rs.next())
              {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDataNascimento(sdf.format(rs.getDate("DATA_NASCIMENTO")));
                cliente.setTelefone(rs.getString("TELEFONE"));
                listaClientes.add(cliente);
              }
              if (!listaClientes.isEmpty())
              {
                String resultDate = "Clientes Cadastrados\n";
                JTextArea saidaArea = new JTextArea();
                saidaArea.setText(resultDate);
                for (Cliente cliente : listaClientes)
                {
                    saidaArea.append(cliente.toString() + "\n");
                }
                saidaArea.setEditable(false);
                JOptionPane.showMessageDialog(null, new JScrollPane(saidaArea),
                        "LISTA DE CLIENTES CADASTRADOS", JOptionPane.INFORMATION_MESSAGE);
              }
              else 
              {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!",
                        "Busca Concluída", JOptionPane.INFORMATION_MESSAGE);
              }
        } 
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi buscar a lista de clientes no Banco, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static Cliente clienteCadastrado(String cpf)
    {
        String sql = "SELECT ID_CLIENTE, NOME, CPF, DATA_NASCIMENTO, TELEFONE FROM CLIENTE WHERE CPF = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            Cliente cliente = new Cliente();
            if (rs.next())
            {
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDataNascimento(sdf.format(rs.getDate("DATA_NASCIMENTO")));
                cliente.setTelefone(rs.getString("TELEFONE"));
                return cliente;
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
                return  null;
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel reaizar a busca do cliente, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        finally
        {
            try
            {
                if(ps!=null)
                {
                    ps.close();
                }
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Não foi possivel encerrar os serviços, exepction: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static Boolean isClienteCadastrado(String cpf)
    {
        String sql = "SELECT ID_CLIENTE, NOME, CPF, DATA_NASCIMENTO, TELEFONE FROM CLIENTE WHERE CPF = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            if(rs.next())
            {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDataNascimento(sdf.format(rs.getDate("DATA_NASCIMENTO")));
                cliente.setTelefone(rs.getString("TELEFONE"));
                return true;
            }
            else
            {
              JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
              return false;
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel reaizar a busca do cliente, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        finally
        {
            try
            {
                if(ps!=null)
                {
                    ps.close();
                }
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Não foi possivel encerrar os serviços, exepction: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static Cliente clienteCadastrado(Integer idCliente)
    {
        String sql = "SELECT ID_CLIENTE, NOME, CPF, DATA_NASCIMENTO, TELEFONE FROM CLIENTE WHERE ID_CLIENTE = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            Cliente cliente = new Cliente();
            if (rs.next())
            {
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setDataNascimento(sdf.format(rs.getDate("DATA_NASCIMENTO")));
                cliente.setTelefone(rs.getString("TELEFONE"));
                return cliente;
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
                return  null;
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel reaizar a busca do cliente, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        finally
        {
            try
            {
                if(ps!=null)
                {
                    ps.close();
                }
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Não foi possivel encerrar os serviços, exepction: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
