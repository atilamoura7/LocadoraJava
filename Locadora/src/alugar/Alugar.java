package alugar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import pessoamodel.Cliente;
import veiculomodel.Veiculo;

public class Alugar
{
    private Integer idAluguel;
    private Cliente cliente;
    private Veiculo veiculo;
    private Integer idCliente;
    private Integer idVeiculo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valor; 
    private Double dias;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   
    public Alugar(Cliente cliente, Veiculo veiculo, String dataInicio, String dataFim)
    {
        this.setCliente(cliente);
        this.setVeiculo(veiculo);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.calcularDias(this.getDataInicio(), this.getDataFim());
        calcularAluguel();
    }
    public Alugar()
    {
        
    }
    public Integer getIdAluguel()
    {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel)
    {
        this.idAluguel = idAluguel;
    }
    
    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public LocalDate getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio)
    {
        
        this.dataInicio = LocalDate.parse(dataInicio, formatter);
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = LocalDate.parse(dataFim, formatter);
    }

  

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    public void setDias(Double dias)
    {
        this.dias = dias;
    }
    private Double getDias()
    {
        return this.dias;
    }

    public void setIdAluguel(Integer idAluguel) {
        this.idAluguel = idAluguel;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    
    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }
    
    public void calcularAluguel()
    {
        Double desc;
        if(this.getDias()>5)
        {
            valor = this.getDias()*70;
            desc = valor *(7/100);
            valor = valor - desc;
            this.setValor(valor);
        }
        else
        {
            valor = this.getDias()*70;
            this.setValor(valor);
        }
    }
    public void calcularDias(LocalDate dataInicio, LocalDate DataFim)
    { 
        Long totDias = ChronoUnit.DAYS.between(dataInicio, dataFim);
        double totalDiasDouble;
        totalDiasDouble = (double) totDias;
        setDias(totalDiasDouble);
    }
    public String toString()
    {
        return "Cliente: "+this.getCliente().getNome()+", Cpf: "+this.getCliente().getCpf()+", Telefone: "+this.getCliente().getTelefone()
                +"\nVeículo Marca: "+this.getVeiculo().getMarca()+", Modelo: "+this.getVeiculo().getModelo()+", Placa: "+this.getVeiculo().getPlaca()+", Data Fim: "+this.getDataFim().format(formatter)+", Valor: R$"+this.getValor()+"\n";
    }
}
    
