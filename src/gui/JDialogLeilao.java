/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import negocio.pojos.Bem;
import negocio.pojos.FormaLance;
import negocio.pojos.Lance;
import negocio.pojos.Leilao;
import negocio.pojos.Natureza;

/**
 *
 * @author marnei
 */
public class JDialogLeilao extends javax.swing.JDialog {

    private LeilaoControlador controlador;
    private JFramePrincipal jFramePrincipal;
    private Leilao leilao;
    private String situacao;

    /**
     * Creates new form JDialogLeilao
     */
    public JDialogLeilao(java.awt.Frame parent, boolean modal, LeilaoControlador controlador, Leilao leilao) throws ParseException {
        super(parent, modal);
        jFramePrincipal = (JFramePrincipal) parent;
        this.controlador = controlador;
        initComponents();
        jTextFieldUsuario.setText(controlador.usuarioLogado.getNome());
        if (leilao != null) {
            this.leilao = leilao;
            preencherCampos();
        } else {
            this.setTitle("Novo leilão");
            this.leilao = new Leilao();
        }
        preencherSituacao();
        
        MaskFormatter formatarData = new MaskFormatter("##/##/####");
        formatarData.setPlaceholderCharacter('_');
        MaskFormatter formatarHora = new MaskFormatter("##:##:##");
        formatarHora.setPlaceholderCharacter('_');
    }

    private void preencherCampos() {

    }

    private void preencherSituacao() {
        if (leilao.getCodigo() == null) {
            situacao = "Novo";
        } else if (leilao.getInicio().after(Calendar.getInstance())) {
            situacao = "Aguardando início";
        } else if ((leilao.getInicio().before(Calendar.getInstance())) && (leilao.getTermino().after(Calendar.getInstance()))) {
            situacao = "Em andamento";
        } else if (leilao.getTermino().before(Calendar.getInstance())) {
            situacao = "Finalizado";
        } else {
            situacao = "Não reconhecido";
        }
    }

    public void preencherTabelaLote() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Código", "Descrição", "Categoria"};
        List<Bem> lote = leilao.getLote().getBens();
        for (Bem item : lote) {
            dados.add(new Object[]{item.getCodigo(),
                item.getDescricao(),
                item.getCategoria().getDescricao()});
        }
        ModelTable modeloTabelaLote = new ModelTable(dados, colunas);
        jTableLote.setModel(modeloTabelaLote);
    }

    public void preencherTabelaLances() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Código", "Usuário", "Data", "Hora", "Valor"};
        List<Lance> lances = leilao.getLances();
        for (Lance item : lances) {
            dados.add(new Object[]{item.getCodigo(),
                item.getUsuario().getNome(),
                item.getData(),
                item.getHora(),
                item.getValor()});
        }
        ModelTable modeloTabelaLances = new ModelTable(dados, colunas);
        jTableLances.setModel(modeloTabelaLances);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonAdicionarBem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldDataInicio = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextFieldHoraInicio = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldDataTermino = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextFieldHoraTermino = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelValor = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldUsuario = new javax.swing.JTextField();
        jTextFieldSituacao = new javax.swing.JTextField();
        jComboBoxNatureza = new javax.swing.JComboBox();
        jComboBoxFormaLance = new javax.swing.JComboBox();
        jPanelLances = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLances = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFormattedTextFieldValor = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableLote = new javax.swing.JTable();
        jButtonOk = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Leilão");

        jLabel11.setText("Situação:");

        jLabel1.setText("Leilão Nº:");

        jLabelUsuario.setText("Vendedor: ");

        jLabel3.setText("Data de início:");

        jLabel4.setText("Data de término:");

        jButtonAdicionarBem.setText("Adicionar...");
        jButtonAdicionarBem.setMaximumSize(new java.awt.Dimension(150, 23));
        jButtonAdicionarBem.setMinimumSize(new java.awt.Dimension(150, 23));
        jButtonAdicionarBem.setPreferredSize(new java.awt.Dimension(150, 23));

        jLabel5.setText("Hora de início:");

        jFormattedTextFieldDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel6.setText("Horá de término:");

        jFormattedTextFieldHoraInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.MEDIUM))));

        jLabel7.setText("Natureza de leilão:");

        jFormattedTextFieldDataTermino.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel8.setText("Forma de realização dos lances:");

        jFormattedTextFieldHoraTermino.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.MEDIUM))));

        jLabel9.setText("Lote de bens:");

        jLabelValor.setText("Preço mínimo: ");

        jTextFieldCodigo.setEditable(false);

        jTextFieldUsuario.setEditable(false);

        jTextFieldSituacao.setEditable(false);

        jComboBoxNatureza.setModel(new DefaultComboBoxModel(Natureza.values()));
        jComboBoxNatureza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNaturezaActionPerformed(evt);
            }
        });

        jComboBoxFormaLance.setModel(new DefaultComboBoxModel(FormaLance.values()));

        jLabel10.setText("Lances:");

        jTableLances.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableLances);

        javax.swing.GroupLayout jPanelLancesLayout = new javax.swing.GroupLayout(jPanelLances);
        jPanelLances.setLayout(jPanelLancesLayout);
        jPanelLancesLayout.setHorizontalGroup(
            jPanelLancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLancesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLancesLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanelLancesLayout.setVerticalGroup(
            jPanelLancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLancesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Editar selecionado...");
        jButton1.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 23));

        jButton2.setText("Excluir selecionado...");
        jButton2.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(150, 23));

        jFormattedTextFieldValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));

        jTableLote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableLote);

        jButtonOk.setText("Ok");
        jButtonOk.setPreferredSize(new java.awt.Dimension(75, 23));
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldUsuario)
                            .addComponent(jFormattedTextFieldDataTermino, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxNatureza, 0, 200, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldDataInicio)
                            .addComponent(jTextFieldSituacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxFormaLance, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabelValor))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldHoraInicio)
                                    .addComponent(jFormattedTextFieldHoraTermino)
                                    .addComponent(jFormattedTextFieldValor)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButtonAdicionarBem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxFormaLance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextFieldDataTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextFieldHoraTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValor)
                    .addComponent(jFormattedTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdicionarBem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelLances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(734, 508));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        leilao.setNatureza((Natureza) jComboBoxNatureza.getSelectedItem());
        leilao.setFormaLance((FormaLance) jComboBoxFormaLance.getSelectedItem());
        leilao.setDataInicio((Date) jFormattedTextFieldDataInicio.getValue());
        leilao.setHoraInicio((Time) jFormattedTextFieldHoraInicio.getValue());
        leilao.setDataTermino((Date) jFormattedTextFieldDataTermino.getValue());
        leilao.setHoraTermino((Time) jFormattedTextFieldHoraTermino.getValue());
        leilao.setUsuario(controlador.usuarioLogado);
        leilao.setPreco((Double) jFormattedTextFieldValor.getValue());

        try {
            controlador.salvarLeilao(leilao);
        } catch (DAOException ex) {
            this.dispose();
            JOptionPane.showMessageDialog(null, ex, "Erro ao salvar leilão", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jComboBoxNaturezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNaturezaActionPerformed
        if ((Natureza) jComboBoxNatureza.getSelectedItem() == Natureza.DEMANDA) {
            jLabelValor.setText("Preço mínimo: ");
            jLabelUsuario.setText("Vendedor: ");
        } else {
            jLabelValor.setText("Preço máximo: ");
            jLabelUsuario.setText("Comprador: ");
        }
    }//GEN-LAST:event_jComboBoxNaturezaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAdicionarBem;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JComboBox jComboBoxFormaLance;
    private javax.swing.JComboBox jComboBoxNatureza;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataInicio;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataTermino;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraInicio;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraTermino;
    private javax.swing.JFormattedTextField jFormattedTextFieldValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelValor;
    private javax.swing.JPanel jPanelLances;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableLances;
    private javax.swing.JTable jTableLote;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldSituacao;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}