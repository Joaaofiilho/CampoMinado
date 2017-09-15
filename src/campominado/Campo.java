package campominado;
/*
    -2: Aberto
    -1: Bomba
    0: Fechado
    1-8: Bomba no local
*/
import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Campo extends javax.swing.JFrame {
    final int wid = 9, hei = 9;
    final int numBombas = (wid*hei)/5;
    JToggleButton caixa[][] = new JToggleButton[wid][hei];
    int numCaixa[][] = new int[wid][hei];
    boolean primeiroMov, podeJogar;
    
    ActionListener listen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0, j = 0;
            boolean achou = false;
            for(i = 0; i < hei; i++){
                for(j = 0; j < wid; j++){
                    if(e.getSource() == caixa[i][j]){
                        achou = true;
                        break;
                    }
                }
                if(achou) break;
            }
            if(podeJogar){
                caixa[i][j].setSelected(true);
                if(!primeiroMov){
                    primeiroMov = true;
                    criar(i, j);
                }
                if(numCaixa[i][j] != -1){
                    abrir(i,j);
                    revelar();  
                }else perder();
                checarVitoria();
            }else revelar();            
        }
    };
    
    private void checarVitoria(){
        boolean ganhou = true;
        for(int i = 0; i < hei; i++){
            for(int j = 0; j < wid; j++){
                if(numCaixa[i][j] == 0){
                    ganhou = false;
                    break;
                }
            }
            if(!ganhou) break;
        }
        if(ganhou){
            javax.swing.JOptionPane.showMessageDialog(null, "Você venceu!");
            podeJogar = false;
        }
    }
    
    private void perder(){
        podeJogar = false;
         for(int i = 0; i < hei; i++){
                for(int j = 0; j < wid; j++){
                    if(numCaixa[i][j] == -1){
                        caixa[i][j].setText("BOOM");
                        caixa[i][j].setSelected(true);
                    }
                }
         }
    }
    
    public void abrir(int y, int x){
        if(x < 0 || y < 0 || x > wid - 1 || y > hei - 1 || numCaixa[y][x] != 0) return;
        int numBombasPerto = 0;
        for(int i = y-1; i <= y + 1; i++){
            for(int j = x-1; j <= x + 1; j++){
                if(!(j < 0 || i < 0 || j > wid - 1 || i > hei - 1) && numCaixa[i][j] == -1){
                    numBombasPerto++;
                }
            }
        }
        if(numBombasPerto == 0){
            numCaixa[y][x] = -2;
                for(int i = y-1; i <= y + 1; i++){
                    for(int j = x-1; j <= x + 1; j++){
                        if(!(j < 0 || i < 0 || j > wid - 1 || i > hei - 1)){
                            if(i != y || j != x) abrir(i, j);
                        }
                }
            }
        }else{
            numCaixa[y][x] = numBombasPerto;
        }
    }
    
    private void revelar(){
        for (int i = 0; i < wid; i++) {
            for (int j = 0; j < hei; j++) {
                if(numCaixa[i][j] == -2){
                    caixa[i][j].setText("");
                    caixa[i][j].setSelected(true);
                }else if(numCaixa[i][j] > 0){
                    caixa[i][j].setText(""+numCaixa[i][j]);
                    caixa[i][j].setSelected(true);
                }else if(numCaixa[i][j] == 0){
                    caixa[i][j].setText("");
                    caixa[i][j].setSelected(false);
                }else if(!podeJogar && numCaixa[i][j] == -1){
                    caixa[i][j].setSelected(true);
                }
            }
        }
        jPanel_Campo.repaint();
    }
    
    private void criar(int y, int x){
        for (int k = 0; k < numBombas; k++) {
            int i = 0, j = 0;
            do{
                i = (int)(Math.random() * (wid + .01));
                j = (int)(Math.random() * (hei + .01));
            }while(numCaixa[i][j] == -1 || (i == y && j == x));
            numCaixa[i][j] = -1;
        }
    }
    
    public Campo() {
        initComponents();
        for (int i = 0; i < wid; i++) {
            for (int j = 0; j < hei; j++) {
                caixa[i][j] = new JToggleButton();
                caixa[i][j].setSize(jPanel_Campo.getWidth()/wid, jPanel_Campo.getHeight()/hei);
                jPanel_Campo.add(caixa[i][j]);
                caixa[i][j].setLocation(j*jPanel_Campo.getWidth()/wid, i*jPanel_Campo.getHeight()/hei);
                caixa[i][j].addActionListener(listen);
            }
        }
        primeiroMov = false;
        podeJogar = true;
        
        
    }
    
    public void resiz(){
        for (int i = 0; i < wid; i++) {
            for (int j = 0; j < hei; j++) {
                caixa[i][j].setSize(jPanel_Campo.getWidth()/wid, jPanel_Campo.getHeight()/hei);
                jPanel_Campo.add(caixa[i][j]);
                caixa[i][j].setLocation(j*jPanel_Campo.getWidth()/wid, i*(jPanel_Campo.getHeight())/hei);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Campo = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(200, 400));

        jPanel_Campo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel_CampoComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel_CampoLayout = new javax.swing.GroupLayout(jPanel_Campo);
        jPanel_Campo.setLayout(jPanel_CampoLayout);
        jPanel_CampoLayout.setHorizontalGroup(
            jPanel_CampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel_CampoLayout.setVerticalGroup(
            jPanel_CampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jMenu1.setText("Jogo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Novo jogo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Campo, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_CampoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel_CampoComponentResized
        resiz();
    }//GEN-LAST:event_jPanel_CampoComponentResized

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        numCaixa = new int[wid][hei];
        revelar();
        podeJogar = true;
        primeiroMov = false;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Campo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel_Campo;
    // End of variables declaration//GEN-END:variables
}
