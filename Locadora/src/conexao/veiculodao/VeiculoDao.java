package conexao.veiculodao;
import conexao.Conexao;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import veiculomodel.Veiculo;
public class VeiculoDao
{
    private static ArrayList<Veiculo> listaVeiculos;
    public VeiculoDao()
    {
        listaVeiculos = new ArrayList<Veiculo>();
    }
    public static void cadastrarVeiculo(Veiculo veiculo)
    {
        String sql = "INSERT INTO VEICULO(MARCA, MODELO, PLACA, DISPONIVEL) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,veiculo.getMarca());
            ps.setString(2,  veiculo.getModelo());
            ps.setString(3, veiculo.getPlaca());
            ps.setBoolean(4, veiculo.isDisponivel());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso! ", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel enviar os dados para o Banco de Dados, exepction: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
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
    public static void listaVeiculos()
    {
        listaVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEICULO";
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
               while(rs.next())
               {
                   Veiculo veiculo = new Veiculo();
                   veiculo.setIdVeiculo(rs.getInt("ID_VEICULO"));
                   veiculo.setMarca(rs.getString("MARCA"));
                   veiculo.setModelo(rs.getString("MODELO"));
                   veiculo.setPlaca(rs.getString("PLACA"));
                   listaVeiculos.add(veiculo);
                }
                if (!listaVeiculos.isEmpty()) 
                {
                    String resultDate = "Veículos Cadastrados\n";
                    JTextArea saidaArea = new JTextArea();
                    saidaArea.setText(resultDate);
                    for (Veiculo veiculo : listaVeiculos)
                    {
                       saidaArea.append(veiculo.toString() + "\n");
                    }
                    saidaArea.setEditable(false);
                    JOptionPane.showMessageDialog(null, new JScrollPane(saidaArea),"LISTA DE VEÍCULOS CADASTRADOS", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Nenhum veiculo cadastrado!",
                        "Busca Concluída", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi buscar a lista de veiculos no Banco, exepction: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void veiculoDisponiveis()
    {
        listaVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEICULO WHERE DISPONIVEL  = '1'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("ID_VEICULO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setPlaca(rs.getString("PLACA"));
                listaVeiculos.add(veiculo);
            }
            if(!listaVeiculos.isEmpty())
            {
                String resultDate = "Veiculos Disponíveis\n";
                JTextArea saidaArea = new JTextArea();
                saidaArea.setText(resultDate);
                for (Veiculo veiculo : listaVeiculos)
                {
                    saidaArea.append(veiculo.toString()+"\n");
                }
                    saidaArea.setEditable(false);
                    JOptionPane.showMessageDialog(null, saidaArea,"LISTA DE VEICULOS DISPONIVEIS", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
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
        
    public static Veiculo veiculoDisponivel(String placa)
    {
        String sql = "SELECT ID_VEICULO, MARCA, MODELO, PLACA, DISPONIVEL FROM VEICULO WHERE PLACA = ? AND DISPONIVEL = '1'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, placa);
            rs = ps.executeQuery();
            Veiculo veiculo = new Veiculo();
            if (rs.next())
            {
                veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("ID_VEICULO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setPlaca(rs.getString("PLACA"));
                return veiculo;
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Veiculo não cadastrado", "Erro na busca", JOptionPane.INFORMATION_MESSAGE);
                 return null;
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
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
    public static Boolean isVeiculoDisponivel(String placa)
    {
        String sql = "SELECT ID_VEICULO, MARCA, MODELO, PLACA, DISPONIVEL FROM VEICULO WHERE PLACA = ? AND DISPONIVEL = '1'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, placa);
            rs = ps.executeQuery();
            if(rs.next())
            {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("ID_VEICULO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setPlaca(rs.getString("PLACA"));
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Veiculo não está disponível", "Erro na busca", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
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
    public static void setVeiculoIndisponivel(Integer id_Veiculo)
    {
        String sql = "UPDATE VEICULO SET DISPONIVEL = '0' WHERE ID_VEICULO= ?";
        PreparedStatement ps = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id_Veiculo);
            ps.execute();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a atualizacão da disponibilidade do veiculo, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
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
    public static Veiculo getVeiculoAlugado(Integer idVeiculo)
    {
        String sql = "SELECT ID_VEICULO, MARCA, MODELO, PLACA, DISPONIVEL FROM VEICULO WHERE ID_VEICULO = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, idVeiculo);
            rs = ps.executeQuery();
            Veiculo veiculo = new Veiculo();
            if (rs.next())
            {
                veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("ID_VEICULO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setPlaca(rs.getString("PLACA"));
                return veiculo;
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Não foi possível encontrar esse veículo", "Erro na busca", JOptionPane.INFORMATION_MESSAGE);
                 return null;
            }
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta, exception: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
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