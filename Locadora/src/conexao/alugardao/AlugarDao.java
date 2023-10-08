package conexao.alugardao;

import alugar.Alugar;
import conexao.Conexao;
import conexao.pessoadao.ClienteDao;
import conexao.veiculodao.VeiculoDao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import pessoamodel.Cliente;
import veiculomodel.Veiculo;

public class AlugarDao
{
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static ArrayList<Alugar> listaAluguel;
    
    public static void registroAluguel(Alugar alugar)
    {
        String sql  = "INSERT INTO ALUGUEL(ID_CLIENTE, ID_VEICULO, DATA_INICIO, DATA_FIM, VALOR) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try
        {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1,alugar.getCliente().getIdCliente());
            ps.setInt(2, alugar.getVeiculo().getIdVeiculo());
            ps.setDate(3, Date.valueOf(alugar.getDataInicio()));
            ps.setDate(4, Date.valueOf(alugar.getDataFim()));
            ps.setDouble(5, alugar.getValor());
            ps.execute();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o aluguel no sistema, exception: "+e, "ERRO NO CADASTRO", JOptionPane.ERROR_MESSAGE);
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
              JOptionPane.showMessageDialog(null, "Não foi possivel encerrar o serviço, exception: "+e, "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void listaAlugueis()
    {
        String sql = "SELECT * FROM ALUGUEL";
        listaAluguel = new ArrayList<Alugar>();
        PreparedStatement ps = null;
        ResultSet rs= null;
        try
        {
           ps = Conexao.getConexao().prepareStatement(sql);
           rs = ps.executeQuery();
           Cliente cliente;
           Veiculo veiculo;
           while(rs.next())
           {
              Alugar alugar = new Alugar();
              alugar.setIdAluguel(rs.getInt("ID_ALUGUEL"));
              alugar.setIdCliente(rs.getInt("ID_CLIENTE"));
              alugar.setIdVeiculo(rs.getInt("ID_VEICULO"));
              alugar.setDataInicio(sdf.format(rs.getDate("DATA_INICIO")));
              alugar.setDataFim(sdf.format(rs.getDate("DATA_FIM")));
              alugar.setValor(rs.getDouble("VALOR"));
              alugar.setCliente(cliente = ClienteDao.clienteCadastrado(alugar.getIdCliente()));
              alugar.setVeiculo(veiculo = VeiculoDao.getVeiculoAlugado(alugar.getIdVeiculo()));
              listaAluguel.add(alugar);
           }
           String resultDate = "Aluguéis Cadastrados\n";
           JTextArea saidaArea = new JTextArea();
           saidaArea.setText(resultDate);
           for(Alugar alugar: listaAluguel)
           {
               saidaArea.append(alugar.toString()+ "\n");
           }
           saidaArea.setEditable(false);
           JOptionPane.showMessageDialog(null, saidaArea,"LISTA DE  ALUGUÉIS", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null,"Não foi possivel realizar a consultar, exception: "+e,"Erro ao consultar Lista de alugueis", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

