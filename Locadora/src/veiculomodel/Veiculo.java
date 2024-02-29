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
   public String formatarPlaca(String placa) {
        // Verifica se a placa tem o formato correto "UUU-9999" ou "UUU-9U99"
        placa = placa.replaceAll("\\s", "");
        if (placa.matches("^[A-Z]{3}[0-9]{1}[A-Z0-9]{1}[0-9]{2}$")) {
            // Aplica a máscara
            placa = placa.substring(0,3).toUpperCase()+" "+placa.substring(3);
            return placa;
        } else {
            // Se a placa não está no formato correto, retorna a placa original
            placa = placa.substring(0,3).toUpperCase()+" "+placa.substring(3);
            return placa;
        }
    }
    public String toString()
    {
       return "Marca: "+this.getMarca()+", Modelo: "+this.getModelo()+", Placa: "+this.getPlaca()+".";
    }
}
