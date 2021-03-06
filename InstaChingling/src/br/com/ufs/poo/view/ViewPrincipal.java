/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufs.poo.view;

import br.com.ufs.poo.dao.ComentarioDAO;
import br.com.ufs.poo.jdbc.ConnectionPool;
import br.com.ufs.poo.dao.FeedDAO;
import br.com.ufs.poo.dao.SeguidorDAO;
import br.com.ufs.poo.modelo.Comentario;
import br.com.ufs.poo.modelo.Feed;
import br.com.ufs.poo.modelo.Seguidor;
import br.com.ufs.poo.modelo.Usuario;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Luiz Souza
 */
public class ViewPrincipal extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form ViewPrincipal
     */
    private int id;
    private String nome;
    private String descricao;
    int top = 0;
    /**
     *
     */
    public void carregar() {
        //txtAreaFeed.setText(null);
        pnlFeed.removeAll();
        txtAreaSeguidores.setText(null);
        txtAreaSeguindo.setText(null);
        top = 0;
        try (Connection con = new ConnectionPool().getConnection()) {
            FeedDAO daoFeed = new FeedDAO(con);
            List<Feed> listaFeed = daoFeed.listaFeed(this.id);
            listaFeed.stream().map((feedAux) -> {
                JPanel pnlAux = new JPanel();
                pnlAux.setLayout(null);
                pnlAux.setBounds(0, top, 700, 400);
                //TEXTO DO FEED
                JLabel lblFeed = new JLabel(feedAux.getNomeUsuario()+": "+feedAux.getTexto());
                lblFeed.setBounds(0,0,400,15);
                Font fonte = new Font("Segoe Ul Emoji", Font.LAYOUT_LEFT_TO_RIGHT, 15);
                lblFeed.setFont(fonte);
                pnlAux.add(lblFeed);
                //IMAGEM DO FEED
                JLabel lblFeedImagem = new JLabel();
                lblFeedImagem.setBounds(0,25,540,308);
                ImageIcon image = new ImageIcon(feedAux.getImagem());  
                lblFeedImagem.setIcon(new ImageIcon(image.getImage().getScaledInstance(lblFeedImagem.getWidth(),lblFeedImagem.getHeight(), Image.SCALE_DEFAULT)));
                pnlAux.add(lblFeedImagem);
                //
                JTextArea comentarios = new JTextArea();
                comentarios.setBounds(545, 25, 150, 280);
                pnlAux.add(comentarios);
                //
                JButton addComentario = new JButton("Comentar");
                addComentario.setBounds(545, 305, 150, 25);
                ButtonHandler handler = new ButtonHandler();
                addComentario.addActionListener(handler);
                addComentario.setActionCommand(String.valueOf(feedAux.getId()));
                pnlAux.add(addComentario);
                
                ComentarioDAO daoComentario = new ComentarioDAO(con);
                try {
                    List<Comentario> comentario = daoComentario.lista(feedAux.getId());
                     for (Comentario comentarioAux : comentario) {
                        comentarios.append(comentarioAux.getNome()+": " + comentarioAux.getComentario() + "\n");
                     }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ViewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //
                return pnlAux;
            }).map((pnlAux) -> {
                //VARIÃ�VEL POSIÃ‡ÃƒO TOP
                pnlFeed.add(pnlAux);
                return pnlAux;
            }).map((_item) -> {
                top += 400;
                return _item;
            }).forEach((_item) -> {
                pnlFeed.setPreferredSize(new Dimension (750, top));
                //txtAreaFeed.append(feedAux.getTexto() + "\n");
                //txtAreaFeed.append(feedAux.getImagem() + "\n");
            });
            SeguidorDAO daoSeg = new SeguidorDAO(con);
            List<String> seguidores = daoSeg.listaSeguidores(this.id);
            seguidores.stream().forEach((seguidor) -> {
                txtAreaSeguidores.append(seguidor + "\n");
            });
            List<String> seguindo = daoSeg.listaSeguindo(this.id);
            seguindo.stream().forEach((seguindoAux) -> {
                txtAreaSeguindo.append(seguindoAux + "\n");
            });
        } catch (SQLException ex) {
            Logger.getLogger(ViewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    	
    public class ButtonHandler implements ActionListener
    {
        //TRATA EVENTO DO BOTÃO
        public void actionPerformed(ActionEvent event)
        {
            ViewAddComentario telaAddComentario = new  ViewAddComentario( Integer.parseInt(event.getActionCommand()), nome );
            telaAddComentario.setVisible(true);
        }
    }

    public void setUsuario(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.descricao = usuario.getDescricao();
        lblNome.setText("UsuÃ¡rio: " + this.nome);
        lblDescricao.setText("Descricao: " + this.descricao);
    }

    public ViewPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        spnlFeed = new javax.swing.JScrollPane();
        pnlFeed = new javax.swing.JPanel();
        btbAtualizar = new javax.swing.JButton();
        btnSerguir = new javax.swing.JToggleButton();
        btnAddFeed = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaSeguindo = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaSeguidores = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 75));

        lblDescricao.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao.setText("jLabel1");

        lblNome.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("jLabel2");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("InstaChingling");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnLogout)
                .addGap(9, 9, 9)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblDescricao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 500));

        pnlFeed.setPreferredSize(new java.awt.Dimension(800, 545));
        pnlFeed.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout pnlFeedLayout = new javax.swing.GroupLayout(pnlFeed);
        pnlFeed.setLayout(pnlFeedLayout);
        pnlFeedLayout.setHorizontalGroup(
            pnlFeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnlFeedLayout.setVerticalGroup(
            pnlFeedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        spnlFeed.setViewportView(pnlFeed);

        btbAtualizar.setText("Atualizar");
        btbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAtualizarActionPerformed(evt);
            }
        });

        btnSerguir.setText("Seguir +");
        btnSerguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerguirActionPerformed(evt);
            }
        });

        btnAddFeed.setText("Add ao FEED");
        btnAddFeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFeedActionPerformed(evt);
            }
        });

        txtAreaSeguindo.setColumns(20);
        txtAreaSeguindo.setRows(5);
        jScrollPane2.setViewportView(txtAreaSeguindo);

        jTabbedPane1.addTab("Seguindo", jScrollPane2);

        txtAreaSeguidores.setColumns(20);
        txtAreaSeguidores.setRows(5);
        jScrollPane3.setViewportView(txtAreaSeguidores);

        jTabbedPane1.addTab("Seguidores", jScrollPane3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSerguir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddFeed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btbAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(spnlFeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(spnlFeed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btbAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSerguir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddFeed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAtualizarActionPerformed
        // TODO add your handling code here:
        carregar();
    }//GEN-LAST:event_btbAtualizarActionPerformed

    private void btnAddFeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFeedActionPerformed
        // TODO add your handling code here:
        ViewAddFeed addFeed = new ViewAddFeed();
        addFeed.setID(this.id);
        addFeed.setVisible(true);
    }//GEN-LAST:event_btnAddFeedActionPerformed

    private void btnSerguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerguirActionPerformed
        // TODO add your handling code here:
        ViewAddSeguir telaAddSeguir = new ViewAddSeguir();
        telaAddSeguir.setID(this.id);
        telaAddSeguir.setVisible(true);
    }//GEN-LAST:event_btnSerguirActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        ViewLogin2 telaLogin = new ViewLogin2();
        telaLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbAtualizar;
    private javax.swing.JToggleButton btnAddFeed;
    private javax.swing.JButton btnLogout;
    private javax.swing.JToggleButton btnSerguir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pnlFeed;
    private javax.swing.JScrollPane spnlFeed;
    private javax.swing.JTextArea txtAreaSeguidores;
    private javax.swing.JTextArea txtAreaSeguindo;
    // End of variables declaration//GEN-END:variables
}
