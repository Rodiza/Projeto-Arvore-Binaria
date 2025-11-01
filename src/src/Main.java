package src;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import arvores.ArvoreBinaria;
import br.com.davidbuzatto.jsge.imgui.GuiInputDialog;
import br.com.davidbuzatto.jsge.math.Vector2;

/**
 * Demonstração visual de Arvore Binária
 * 
 * @author Rodrigo Costa Garcia, Davi Beli Rosa
 */
public class Main extends EngineFrame {
    
    private GuiButton btnAddElemento;
    private GuiInputDialog inputAddElemento;
    //arvere
    private ArvoreBinaria<Integer, String> arvore;
    private Vector2 posicao;
    
    //A arvore tem uma chave e um valor, só a chave é comparada
    //quando vai decidir pra que lado a node vai, então o valor
    //vou tentar deixar uma string vazia como padrao pra ver no que da
    private String valorPadrao = "";
    
    
    public Main() {
        
        super(
            1000,                 // largura                      / width
            600,                 // algura                       / height
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
        arvore = new ArvoreBinaria<>();
        posicao = new Vector2(100, 200);
        
        btnAddElemento = new GuiButton(430, 100, 140, 40, "Adicionar Elemento", this);
        tratarBotao(btnAddElemento);
        
        inputAddElemento = new GuiInputDialog("Inserir Elemento", 
                "Insira um elemento na árvore: ", 
                true, this);
        tratarInputBox(inputAddElemento);
        
    }


    @Override
    public void update( double delta ) {
        btnAddElemento.update(delta);
        inputAddElemento.update(delta);
        
        if(btnAddElemento.isMousePressed()){
            inputAddElemento.show();
        }
        logicaInput(inputAddElemento);
        
    }
    

    @Override
    public void draw() {
        btnAddElemento.draw();
        inputAddElemento.draw();
    }
    
    private void drawNode(ArvoreBinaria<Integer, String> tree, Vector2 pos ){
        drawCircle(pos, 40, BLACK);
        drawText(tree.get(5), pos, PINK);
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
    
    //Também para diminuir qntd de código no update
    //Só vai checar os botoes de ok, cancelar, etc
    private void logicaInput(GuiInputDialog caixa){
        int valor = 0;
        
        if ( caixa.isCloseButtonPressed() || caixa.isCancelButtonPressed() ) {
            caixa.hide();
        }
        
        if(ehInt(caixa.getValue())){
            valor = Integer.parseInt(caixa.getValue());
        } else{
            caixa.setTitleBarBackgroundColor(RED);
            caixa.setText("Precisa ser um número");
        }
        
        if(caixa.isEnterKeyPressed() || caixa.isOkButtonPressed()){
            arvore.put(valor, valorPadrao);
            caixa.hide();
        }
        
        drawNode(arvore, posicao);
        
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
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
