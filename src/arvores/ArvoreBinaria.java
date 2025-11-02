package arvores;

import java.awt.Color;
import java.util.Iterator;

/* 
* @param <Key> Tipo das chaves que serão armazenadas na árvore.
* @param <Value> Tipo dos valores associados às chaves armazenadas na árvore.
*/
public class ArvoreBinaria<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    /*
     * Classe interna estática que define os nós da árvore binária de busca.
     */
    public static class Node<Key extends Comparable<Key>, Value>{
        
        public Key key;
        public Value value;
        public Node<Key, Value> left;
        public Node<Key, Value> right;
        public int nivel;
        public int ranque;
        public Color cor;
        
        @Override
        public String toString() {
            return key + " -> " + value + " nivel: " + nivel + " ranque: " + ranque;
        }
        
    }
    
    // raiz da árvore
    private Node<Key, Value> root;
    //tamanho da árvore
    private int size;
    
    //Constrói uma árvore binária de busca vazia.
    public ArvoreBinaria() {
        root = null;   // redundante, apenas para mostrar o que acontece
    }
    
    
    public void put( Key key, Value value ) throws IllegalArgumentException {
        
        if ( key == null ) {
            throw new IllegalArgumentException( "first argument to put() is null" );
        }
        
        if ( value == null ) {
            delete( key );
            return;
        }
        
        root = put( root, key, value );
        
    }
    
    //método privado para inserção recursiva
    private Node<Key, Value> put( Node<Key, Value> node, Key key, Value value ) {
        
        if ( node == null ) {

            node = new Node<>();
            node.key = key;
            node.value = value;
            node.left = null;
            node.right = null;
            
            size++;

        } else {
            
            int comp = key.compareTo( node.key );
            
            if ( comp < 0 ) {
                node.left = put( node.left, key, value );
            } else if ( comp > 0 ) {
                node.right = put( node.right, key, value );
            } else {
                node.value = value;
            }
            
        }

        return node;

    }
    
    public Value get( Key key ) throws IllegalArgumentException {
        if ( key == null ) {
            throw new IllegalArgumentException( "argument to get() is null" );
        }
        return get( root, key );
    }

    private Value get( Node<Key, Value> node, Key key ) {
        
        while ( node != null ) {
            
            int comp = key.compareTo( node.key );
            
            if ( comp < 0 ) {
                node = node.left;
            } else if ( comp > 0 ) {
                node = node.right;
            } else {
                return node.value;
            }
            
        }

        return null;

    }
    
    public void delete( Key key ) throws IllegalArgumentException {

        if ( key == null ) {
            throw new IllegalArgumentException( "argument to delete() is null" );
        }

        root = delete( root, key );

    }
    
    /*
     * Método privado para a remoção recursiva (Hibbard Deletion).
     */
    private Node<Key, Value> delete( Node<Key, Value> node, Key key ) {
        
        if (node == null) return null;

        int comp = key.compareTo(node.key);

        if (comp < 0) {
            node.left = delete(node.left, key);
        } else if (comp > 0) {
            node.right = delete(node.right, key);
        } else {
           // nó encontrado
            size--;

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // dois filhos
            Node<Key, Value> t = node;
            Node<Key, Value> successor = min(t.right); // menor da subárvore direita

            // Se o sucessor não é o filho direto
            if (successor != t.right) {
                successor.right = deleteMin(t.right);
            } else {
                successor.right = t.right.right; // reaponta corretamente
            }

            successor.left = t.left;
            node = successor;
            
            }

        return node;

    }
    
    private Node<Key, Value> min(Node<Key, Value> node) {
        while (node.left != null) node = node.left;
        return node;
    }
    
    private Node<Key, Value> deleteMin(Node<Key, Value> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }
    
    public boolean contains( Key key ) throws IllegalArgumentException {
        return get( key ) != null;
    }
    
    public void clear() {
        root = clear( root );
        size = 0;
    }
    
    /*
     * Método privado para remoção de todos os itens de forma recursiva.
     */
    private Node<Key, Value> clear( Node<Key, Value> node ) {

        if ( node != null ) {
            node.left = clear( node.left );
            node.right = clear( node.right );
        }

        return null;

    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int getSize() {
        return size;
    }
    
    
    //Implementar depois
    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException("Criar o iterador.");
    }
    
    public Node<Key, Value> getRoot() {
        
        return root;
    }
    
    //nivel indica a profundidade (posicionamento vertical)
    //ranque usado para posicionamento horizontal
    public void atualizarRanqueNivel() {
        
        int[] contadorRanque = new int[]{0};
        atualizarRanqueNivel( root, 0, contadorRanque );
        
    }
    
    private void atualizarRanqueNivel( Node<Key, Value> node, int nivel, int[] contadorRanque ) {
        
        if( node == null ) {
            
            return;
        }
        
        atualizarRanqueNivel( node.left, nivel + 1, contadorRanque );
        node.nivel = nivel;
        node.ranque = contadorRanque[0];
        contadorRanque[0]++;
        atualizarRanqueNivel( node.right, nivel + 1, contadorRanque );
        
    }
    
}
