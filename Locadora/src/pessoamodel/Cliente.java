package pessoamodel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente 
{
    private int idCliente;
    private String nome, cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Cliente(String nome, String cpf, String dataNascimento)
    {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);
    }

    public Cliente()
    {
        
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }
    
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf =   formatarCpf(cpf);
    }

    public String getTelefone() 
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = aplicarMascara(telefone);
    }
    public String formatarCpf(String cpf) 
    {
        // Verifica se o CPF tem o formato correto (11 dígitos)
        if (cpf.matches("^[0-9]{11}$")) {
            // Aplica a máscara "XXX.XXX.XXX-XX"
            return String.format("%s.%s.%s-%s",
                    cpf.substring(0, 3),
                    cpf.substring(3, 6),
                    cpf.substring(6, 9),
                    cpf.substring(9));
        } else {
            // Se o CPF não está no formato correto, retorna o CPF original
            return cpf;
        }
    }
    public String formataCpf(String cpf)
    {
        if(cpf.length()==11)
        {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            return cpf;
        }
        else
        {
            System.out.println("Informe um cpf válido!");
            return null;
        }
        
    }
    public void setDataNascimento(String dataNascimento)
    {
       this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }
    
    public LocalDate getDataNascimento()
    {
        return this.dataNascimento;
    }
    public String toString()
    {
        return"Nome: "+this.getNome()+", Cpf: "+this.getCpf()+", Telefone: "+this.getTelefone()+", Data de Nascimento: "+this.getDataNascimento().format(formatter)+"."; 
    }

    public String aplicarMascara(String numeroTelefone) {
        // Remove todos os caracteres que não são dígitos
        String numeroLimpo = numeroTelefone.replaceAll("[^\\d]", "");

        // Verifica se o número possui 11 dígitos
        if (numeroLimpo.length() != 11) {
            throw new IllegalArgumentException("O número de telefone deve conter 11 dígitos.");
        }

        // Aplica a máscara "(XX) XXXXX-XXXX"
        String numeroFormatado = "(" + numeroLimpo.substring(0, 2) + ") "
                + numeroLimpo.substring(2, 7) + "-"
                + numeroLimpo.substring(7, 11);

        return numeroFormatado;
    }
}
 