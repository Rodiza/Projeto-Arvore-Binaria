package src;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import arvores.ArvoreAVL;
import br.com.davidbuzatto.jsge.core.Camera2D;
import br.com.davidbuzatto.jsge.imgui.GuiInputDialog;
import br.com.davidbuzatto.jsge.math.Vector2;

/**
 * Demonstração visual de Arvore AVL
 * 
 * @author Rodrigo Costa Garcia, Davi Beli Rosa
 */
public class ArvoreAVLVisualizador extends EngineFrame {
    
    private GuiButton btnAddElemento;
    private GuiInputDialog inputAddElemento;
    private GuiButton btnDeletarElemento;
    private GuiInputDialog inputDeletarElemento;
    
    //arvore
    private ArvoreAVL<Integer, String> arvore;
    
    //atributos da arvore
    private Vector2 origem;
    
    //camera
    private Camera2D camera;
    
    /*A arvore tem uma chave e um valor, só a chave é comparada
    quando vai decidir pra que lado a node vai, então o valor
    vou tentar deixar uma string vazia como padrao pra ver no que da*/
    private String valorPadrao = "";
    
    
    public ArvoreAVLVisualizador() {
        
        super(
            1000,                 // largura                      / width
            600,                 // altura                      / height
            "Visualizador de Árvore Binária",      // título                       / title
            60,                  // quadros por segundo desejado / target FPS
            true,                // suavização                   / antialiasing
            false,               // redimensionável              / resizable
            false,               // tela cheia                   / full screen
            false,               // sem decoração                / undecorated
            false,               // sempre no topo               / always on top
            false                // fundo invisível              / invisible background
        );
        
    }
    

    @Override
    public void create() {
        
        arvore = new ArvoreAVL<>();
        origem = new Vector2( 500, 150 );
            
        //botão e input para adicionar nó
        btnAddElemento = new GuiButton(360, 30, 140, 40, "Adicionar Elemento", this);
        tratarBotao(btnAddElemento);
        
        inputAddElemento = new GuiInputDialog
                (
                        
                    "Inserir Elemento", 
                    "Insira um elemento na árvore: ", 
                    true, 
                    this
                        
                );
        tratarInputBox(inputAddElemento);
        
        //botão e input para deletar nó
        btnDeletarElemento = new GuiButton( 520, 30, 140, 40, "Deletar elemento", this );
        tratarBotao( btnDeletarElemento );
        
        inputDeletarElemento = new GuiInputDialog 
                ( 
                        
                    "Deletar Elemento", 
                    "Insira o elemento a ser removido: ", 
                    true, 
                    this 
                        
                );
        tratarInputBox( inputDeletarElemento );
        
        //criando camera
        camera = new Camera2D();
        
    }


    @Override
    public void update( double delta ) {
        btnAddElemento.update( delta );
        inputAddElemento.update( delta );
        btnDeletarElemento.update( delta );
        inputDeletarElemento.update( delta );
        
        if(btnAddElemento.isMousePressed()){
            
            inputAddElemento.show();
            
        }
        
        if(btnDeletarElemento.isMousePressed()){
            
            inputDeletarElemento.show();
            
        }
        
        logicaInputAdicionar( inputAddElemento );
        logicaInputDeletar( inputDeletarElemento );
        
        
        //zoom e unzoom da camera
        if ( isKeyDown( KEY_KP_ADD ) || isKeyDown( KEY_EQUAL ) ) {
            camera.zoom += 0.01;
        } else if ( isKeyDown( KEY_KP_SUBTRACT ) || isKeyDown( KEY_MINUS ) ) {
            camera.zoom -= 0.01;
            if ( camera.zoom < 0.1 ) {
                camera.zoom = 0.1;
            }
        }
        
        
        //volta para 
        if ( isKeyDown( KEY_R ) ) {
            
            camera.zoom = 1;
            
        }
        
        //Controle da camera nas setas
        if(isKeyDown(KEY_UP)){
            camera.offset.y += 10;
        }else if(isKeyDown(KEY_DOWN)){
            camera.offset.y -= 10;
        } else if(isKeyDown(KEY_LEFT)){
            camera.offset.x += 10;
        } else if(isKeyDown(KEY_RIGHT)){
            camera.offset.x -= 10;
        }

        
    }
    

    @Override
    public void draw() {
        
        
        
        beginMode2D( camera );
        
        if( !arvore.isEmpty() && arvore != null ) {
            
            desenhar( arvore.getRoot(), origem.x, origem.y, 200 );
            
        }
        
        endMode2D();
        btnAddElemento.draw();
        inputAddElemento.draw();
        btnDeletarElemento.draw();
        inputDeletarElemento.draw();
        
    }
   
    
    //metodo recursivo para desenhar nodes e arestas
    private void desenhar( ArvoreAVL.Node<Integer, String> node, double x, double y, double offset ) {
        
        if( node == null ) {
            
            return;    
        }
        
        //desenhar node atual
        double raio = 20;
        fillCircle( new Vector2( x, y ), raio, WHITE );
        drawCircle( new Vector2( x, y ), raio, BLACK );
        drawText( node.key.toString(), x - 5, y - 5, BLACK );
        
        //desenha filho da esquerda
        if( node.left != null ) {
            
            double filhoX = x - offset;
            double filhoY = y + 100;
            
            double dx = filhoX - x;
            double dy = filhoY - y;
            double dist = Math.sqrt( dx * dx + dy* dy );
            
            double inicioX = x + dx / dist * raio;
            double inicioY = y + dy / dist * raio;
            double fimX = filhoX - dx / dist * raio;
            double fimY = filhoY - dy / dist * raio;
            
            drawLine( inicioX, inicioY, fimX, fimY, BLACK );   //desenha aresta para esquerda
            desenhar( node.left, filhoX, filhoY, offset / 2 );
            
        }
        
        //desenha filho da direta
        if( node.right != null ) {
            
            
            double filhoX = x+ offset;
            double filhoY = y + 100;
            
            double dx = filhoX - x;
            double dy = filhoY - y;
            double dist = Math.sqrt( dx * dx + dy* dy );
            
            double inicioX = x + dx / dist * raio;
            double inicioY = y + dy / dist * raio;
            double fimX = filhoX - dx / dist * raio;
            double fimY = filhoY - dy / dist * raio;
            
            drawLine( inicioX, inicioY, fimX,fimY, BLACK );   //desenha aresta direita
            desenhar( node.right, filhoX, filhoY, offset / 2 );
            
        }
        
    }
    
    //Isso aqui é só pra diminuir a quantidade de código no create
    //Só vai deixar o botao mais bonitinho
    private void tratarBotao(GuiButton btn){
        btn.setBackgroundColor(DARKGREEN);
        btn.setTextColor(BLACK);
        btn.setBorderColor(BLACK);
    }
    
    //Mesmo que a função de cima só que para input box
    private void tratarInputBox(GuiInputDialog caixa){
        caixa.setBorderColor(BLACK);
        caixa.setTitleBarBackgroundColor(DARKGREEN);
        caixa.setTitleBarTextColor(BLACK);
        caixa.setTextColor(BLACK);
    }
    
    //Lógica do input para adicionar um elemento
    //Diminui o código no update
    private void logicaInputAdicionar(GuiInputDialog caixa){
        
        if ( caixa.isCloseButtonPressed() || caixa.isCancelButtonPressed() ) {
            
            caixa.hide();
            return;
            
        }
        
        if(caixa.isEnterKeyPressed() || caixa.isOkButtonPressed()){
            
            if(  ehInt( caixa.getValue() ) ) {
                
                int valor = Integer.parseInt( caixa.getValue() );
                arvore.put( valor, valorPadrao );
                caixa.hide();
                
            } else {
                
                caixa.setTitleBarBackgroundColor( RED );
                caixa.setText( "Insira um número");
                
            }
            
        }
        
    }
    
    //Lógica do input para deletar um elemento
    //Diminui o código no update
    private void logicaInputDeletar(GuiInputDialog caixa){
        
        if ( caixa.isCloseButtonPressed() || caixa.isCancelButtonPressed() ) {
            
            caixa.hide();
            return;
            
        }
        
        if(caixa.isEnterKeyPressed() || caixa.isOkButtonPressed()){
            
            if(  ehInt( caixa.getValue() ) ) {
                
                int valor = Integer.parseInt( caixa.getValue() );
                arvore.delete( valor );
                caixa.hide();
                
            } else {
                
                caixa.setTitleBarBackgroundColor( RED );
                caixa.setText( "Insira um número");
                
            }
            
        }
        
    }
    
    
    //Checa que se é a string é um numero
    private boolean ehInt(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
