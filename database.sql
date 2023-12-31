CREATE TABLE ECOMMERCE.VENDA
(
    ID                       NUMBER(38, 0) NOT NULL,
    CANAL                    VARCHAR2(100) NOT NULL,
    CODIGO_EMPRESA           INTEGER       NOT NULL,
    CODIGO_LOJA              INTEGER       NOT NULL,
    NUMERO_PDV               INTEGER       NOT NULL,
    NUMERO_PEDIDO            VARCHAR2(38)  NOT NULL,
    NUMERO_ORDEM_EXTERNO     VARCHAR2(38)  NOT NULL,
    VALOR_TOTAL              NUMBER(38, 2) NOT NULL,
    QTD_ITEM                 NUMBER(38,0)  NOT NULL,
    VENDA_REQUEST            CLOB          NOT NULL,
    DATA_ATUALIZACAO         TIMESTAMP     NOT NULL,
    DATA_REQUISICAO          TIMESTAMP     NOT NULL,
    CHAVE_NFE                VARCHAR2(44),
    NUMERO_NOTA              NUMBER(38,0),
    DATA_EMISSAO             TIMESTAMP,
    PDF                      CLOB,
    SITUACAO                 VARCHAR2(100) NOT NULL,
    MOTIVO                   VARCHAR2(255),
    CONSTRAINT PK_DEVOLUCAO_PEDIDO PRIMARY KEY (ID)
)
    TABLESPACE ECOMMERCE_DAT;

CREATE SEQUENCE ECOMMERCE.VENDA_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 20 NOORDER NOCYCLE;