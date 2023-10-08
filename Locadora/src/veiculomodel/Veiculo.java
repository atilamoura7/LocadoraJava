package veiculomodel;

import conexao.veiculodao.VeiculoDao;

public class Veiculo
{
   private int idVeiculo;
   private String marca, modelo,placa;
   private boolean disponivel;
   
    public Veiculo()
    {
        
    }
    public int getIdVeiculo()
    {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo)
    {
        this.idVeiculo = idVeiculo;
    }
   
   public void setMarca(String marca)
   {
       this.marca = marca;
   }
   
   public String getMarca()
   {
       return this.marca;
   }
   
   public void setModelo(String modelo)
   {
       this.modelo = modelo;
   }
   
   public String getModelo()
   {
       return this.modelo;
   }
   
   public void setPlaca(String placa)
   {
       this.placa = formatarPlaca(placa);
   }
   
    public String getPlaca()
   {
       return this.placa;
   }
   
   public void setDisponivel(boolean disponivel)
   {
       this.disponivel = disponivel;
   }
   public boolean isDisponivel()
   {
       return this.disponivel;
   }
   private static String formatarPlaca(String placa) {
        // Verifica se a placa tem o formato correto (3 letras seguidas de 4 números)
        if (placa.matches("^[A-Z]{3}[0-9]{4}$")) {
            // Aplica a máscara "UUU-9999"
            return String.format("%s-%s", placa.substring(0, 3), placa.substring(3));
        } else {
            // Se a placa não está no formato correto, retorna a placa original
            return placa;
        }
    }
    public String toString()
    {
       return "Marca: "+this.getMarca()+", Modelo: "+this.getModelo()+", Placa: "+this.getPlaca()+".";
    }
}
