package transferencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    Cliente budie;
    Cliente olga;
    Conta contaBudie;
    Conta contaOlga;

    @BeforeEach
    void setUp() {
        budie = new Cliente("Budie", "12345678900", "987654321");
        olga = new Cliente("Olga", "00987654321", "123456789");

        contaBudie = new Conta("0001", "1728", 2500.00, budie);
        contaOlga = new Conta("0113", "11312", 3500.00, olga);
    }

    @Test
    public void realizarTransacao(){
        contaBudie.realizaTransferencia(1000, contaOlga);

        assertEquals(1500.00, contaBudie.getSaldo());
        assertEquals(4500.00, contaOlga.getSaldo());

        System.out.println("Saldo Conta Budie: " + contaBudie.getSaldo());
        System.out.println("Saldo Conta Olga: " + contaOlga.getSaldo());
    }

    @Test
    public void validarTransferenciaInvalida(){
        boolean result = contaBudie.realizaTransferencia(3500.00, contaOlga);
        assertFalse(result);
    }

    @Test
    public void validarProprietario(){
        assertEquals(budie, contaBudie.getProprietario());
        System.out.println("O Proprietário da Conta é: " + budie.getNome() + " do CPF: " + budie.getCpf() + " e do RG: " + budie.getRg());
    }

    @Test
    public void validarDeposito(){
        contaBudie.realizarDeposito(2000);
        assertEquals(4500.00, contaBudie.getSaldo());

        System.out.println("Depósito Efetuado na Conta de: " + budie.getNome() + " Agência: " + contaBudie.getAgencia() + " Conta: " + contaBudie.getNumeroConta());
        System.out.println("O novo Saldo é: " + contaBudie.getSaldo());
    }

    @Test
    public void validaSaque(){
        contaOlga.realizarSaque(1000);
        assertEquals(2500.00, contaOlga.getSaldo());
        System.out.println("O novo saldo da Conta da Sra. " + olga.getNome() + " é: " + contaOlga.getSaldo());
    }

}