package org.soaring.emailage.data;

import org.hibernate.validator.constraints.Email;

public enum FControlFraudReportCause {
    TODOS(0, "Todos", EmailageFraudCode.OTHER),
    CARTAO_TERCEIRO_NAO_LOCALIZADO(1, "Cartão de terceiro não localizado.", EmailageFraudCode.OTHER),
    DESISTENCIA_COMPRADOR_TITULAR_CARTAO(2, "Desistência do comprador ou titular do cartão", EmailageFraudCode.OTHER),
    DESISTENCIA_LOJISTA(3, "Desistência do lojista", EmailageFraudCode.OTHER),
    DUPLICIDADE(4, "Duplicidade", EmailageFraudCode.OTHER),
    ENDERECO_INCOMPLETO(5, "Endereço incompleto", EmailageFraudCode.OTHER),
    HISTORICO_RESTRITIVO_MERCADO(6, "Histórico restritivo no mercado", EmailageFraudCode.OTHER),
    LOJISTA_SUSPEITO(7, "Lojista suspeito", EmailageFraudCode.OTHER),
    TELEFONE_INCOMPLETO(8, "Telefone incompleto", EmailageFraudCode.OTHER),
    TENTATIVAS_CONTATO_SEM_SUCESSO(9, "Tentativas de contato sem sucesso", EmailageFraudCode.OTHER),
    LOJISTA_FRAUDULENTO(10, "Lojista fraudulento", EmailageFraudCode.OTHER),
    OUTROS(11, "Outros", EmailageFraudCode.OTHER),
    CARTAO_CREDITO_EMITIDO_EXTERIOR(12, "Cartão de crédito emitido exterior", EmailageFraudCode.OTHER),
    CARTAO_TERCEIRO_NAO_IDENTIFICADO(13, "Cartão de terceiro não identificado", EmailageFraudCode.OTHER),
    DADOS_PESSOAIS_INVALIDOS(14, "Dados pessoais inválidos", EmailageFraudCode.OTHER),
    ENDERECO_DIVERGENTE(15, "Endereço divergente", EmailageFraudCode.OTHER),
    HISTORICO_FRAUDE_FCONTROL(16, "Histórico de fraude no FControl", EmailageFraudCode.SUSPECTED_FRAUD),
    HISTORICO_TRANSACOES_SUSPEITAS_FCONTROL(17, "Histórico de transações suspeitas no FControl", EmailageFraudCode.SUSPECTED_FRAUD),
    TELEFONE_DIVERGENTE(18, "Telefone divergente", EmailageFraudCode.OTHER),
    TELEFONE_INVALIDO(19, "Telefone inválido", EmailageFraudCode.OTHER),
    COMPRADOR_NAO_RECONHECE_TRANS_PERDA_FINANC(20, "Comprador não reconhece transação com perda financeira", EmailageFraudCode.SUSPECTED_FRAUD),
    TITULAR_CARTAO_NAO_RECONHECE_TRANS_PERDA_FINANC(21, "Titular do cartão não reconhece transação com perda financeira", EmailageFraudCode.SUSPECTED_FRAUD),
    HISTORICO_TRANSACAO_RECUPERACAO_PERDAS(22, "Histórico de transação em recuperação de perdas", EmailageFraudCode.SUSPECTED_FRAUD),
    COMPORTAMENTO_SUSPEITO_CONTATO(23, "Comportamento suspeito durante contato", EmailageFraudCode.SUSPECTED_FRAUD),
    PEDIDO_EXPIRADO(24, "Pedido expirado", EmailageFraudCode.OTHER),
    VENDA_NAO_RECUPERADA(25, "Venda não recuperada", EmailageFraudCode.OTHER),
    CANCELAMENTO_AUTOMATICO_RECUPERACAO_PERDA(26, "Cancelamento automático de recuperação de perda após 48 horas", EmailageFraudCode.OTHER),
    AUTO_FRAUDE_PERDA_FINANCEIRA(27, "Auto-fraude com perda financeira", EmailageFraudCode.CARD_NOT_PRESENT_FRAUD),
    FRAUDE_IDENTIDADE(28, "Fraude de identidade", EmailageFraudCode.IDENTITY_THEFT_FRAUD_APPLICATION),
    FRAUDES_VINCULADAS_PERDA_FINANCEIRA(29, "Fraudes vinculadas com perda financeira", EmailageFraudCode.CUSTOMER_DISPUTE_CBK),
    CHARGEBACK_PEDIDO_ANTERIOR_PERDA_FINANC(30, "Chargeback em pedido anterior do cliente com perda financeira", EmailageFraudCode.OTHER),
    AUTO_FRAUDE_SEM_PERDA_FINANCEIRA(31, "Auto-fraude sem perda financeira", EmailageFraudCode.SUSPECTED_FRAUD),
    COMPRADOR_NAO_RECONHECE_TRANSACAO_SEM_PERDA_FINANC(32, "Comprador não reconhece transação sem perda financeira", EmailageFraudCode.SUSPECTED_FRAUD),
    TITULAR_CARTAO_NAO_RECONHECE_TRANSACAO_SEM_PERDA_FINANC(33, "Titular do cartão não reconhece transação sem perda financeira", EmailageFraudCode.SUSPECTED_FRAUD),
    DESACORDO_COMERCIAL(34, "Desacordo comercial", EmailageFraudCode.OTHER),
    FRAUDES_SEM_PERDA_FINANCEIRA(35, "Fraude vinculada sem perda financeira", EmailageFraudCode.CARD_NOT_PRESENT_FRAUD),
    CBK_ANTERIOR(36, "Chargeback em pedido anterior do cliente sem perda financeira", EmailageFraudCode.SUSPECTED_FRAUD),
    BOLETO_VENCIDO(37, "Boleto vencido", EmailageFraudCode.OTHER),
    TENTATIVAS_TEF_ESGOTADAS(38, "Número de tentativas de TEF esgotadas", EmailageFraudCode.OTHER),
    ESTOQUE_ESGOTADO(39, "Estoque esgotado", EmailageFraudCode.OTHER),
    INSATISFACAO_PRODUTO(40, "Insatisfação com o produto", EmailageFraudCode.OTHER),
    ATRASO_TROCA(41, "Atraso troca", EmailageFraudCode.OTHER),
    PRODUTO_ERRADO(42, "Produto errado", EmailageFraudCode.OTHER),
    CONFIRMACAO_CONTATO_TELEFONICO(43, "Confirmação contato telefônico", EmailageFraudCode.OTHER),
    CONFIRMACAO_CONSULTA_PLUS(44, "Confirmação consulta Plus", EmailageFraudCode.OTHER),
    CONFIRMACAO_CONSULTA_ECOMMERCE(45, "Confirmação consulta Ecommerce", EmailageFraudCode.OTHER),
    CONFIRMACAO_CONSULTA_PESSOAL(46, "Confirmação consulta Pessoal", EmailageFraudCode.OTHER),
    IDENTIFICACAO_SALDO_ORIGEM_SUSPEITA(47, "Identificação de saldo de origem suspeita", EmailageFraudCode.OTHER),
    CARTAO_INVALIDO_OPERACAO(48, "Cartão inválido para a operação", EmailageFraudCode.OTHER),
    BANCO_EMISSOR_CARTAO_INDISPONIVEL(49, "Banco emissor do cartão indisponível", EmailageFraudCode.OTHER),
    PARCELAMENTO_INVALIDO(50, "Parcelamento inválido", EmailageFraudCode.OTHER),
    LIMITE_INSUFICIENTE(51, "Limite insuficiente", EmailageFraudCode.OTHER),
    NUMERO_CARTAO_INVALIDO(52, "Número do cartão inválido", EmailageFraudCode.OTHER),
    RECUSA_BANCO(53, "Recusa do banco", EmailageFraudCode.OTHER),
    ERRO_AUTENTICACAO(54, "Erro durante a autenticação", EmailageFraudCode.OTHER),
    TRANSACAO_NAO_PERMITIDA(55, "Transação não permitida", EmailageFraudCode.OTHER),
    LIGUE_BANCO_EMISSOR(56, "Ligue para o banco emissor", EmailageFraudCode.OTHER),
    NAO_AUTORIZADO_COMPRAS_ONLINE(57, "Não autorizado compras online", EmailageFraudCode.OTHER),
    TRANSACAO_INVALIDA(58, "Transação inválida", EmailageFraudCode.OTHER),
    TRANSACAO_NAO_PROCESSA(59, "Transação não pode ser processada", EmailageFraudCode.OTHER),
    SISTEMA_BANCO_INDISPONIVEL(60, "Sistema do banco indisponível", EmailageFraudCode.OTHER),
    CARTAO_NUMERO_VALIDADE_CVV_INVALIDO(61, "Número do cartão, data de validade ou código de segurança inválido.", EmailageFraudCode.OTHER),
    BANCO_SOLICITOU_REFAZER_TRANSACAO(62, "Banco emissor solicitou refazer transação", EmailageFraudCode.OTHER);


    FControlFraudReportCause(Integer id, String description, EmailageFraudCode emailageFraudCode) {
        this.id = id;
        this.description = description;
        this.emailAgeFraudCode = emailageFraudCode;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public EmailageFraudCode getEmailAgeFraudCode() {
        return emailAgeFraudCode;
    }

    public static FControlFraudReportCause findById(Integer id) {
        if(id == null) {
            return FControlFraudReportCause.OUTROS;
        }

        for(FControlFraudReportCause item : values()) {
            if(id == item.getId()) {
                return item;
            }
        }

        return FControlFraudReportCause.OUTROS;
    }

    private Integer id;
    private String description;
    private EmailageFraudCode emailAgeFraudCode;

}
