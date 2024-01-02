package br.com.renner.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "venda")
@Table(name = "VENDA")
public class DadosVendaEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CANAL")
    private String canal;

    @Column(name = "CODIGO_EMPRESA")
    private Integer cogigoEmpresa;

    @Column(name = "CODIGO_LOJA")
    private Integer cogigoLoja;

    @Column(name = "NUMERO_PDV")
    private Integer numeroPdv;

    @Column(name = "NUMERO_PEDIDO")
    private String numeroPedido;

    @Column(name = "NUMERO_ORDEM_EXTERNO")
    private String numeroOrdemExterno;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Column(name = "QTD_ITEM")
    private BigInteger quantidadeItem;

    //@Lob
    @Column(name = "VENDA_REQUEST")
    private String vendaRequest;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @Column(name = "DATA_REQUISICAO")
    private LocalDateTime dataRequisicao;

    @Column(name = "CHAVE_NFE")
    private String chaveNfe;

    @Column(name = "NUMERO_NOTA")
    private BigInteger numeroNota;

    @Column(name = "DATA_EMISSAO")
    private LocalDateTime dataEmissao;

    //@Lob
    @Column(name = "PDF")
    private String pdf;

    @Column(name = "SITUACAO")
    private String situacao;

    @Column(name = "MOTIVO")
    private String motivo;

}
