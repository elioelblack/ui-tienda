package com.uitienda.managedBean;

import com.tienda.soap.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import com.tienda.soap.Producto;
import com.tienda.soap.ProductoCategoria;
import com.tienda.soap.TiendaWS;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.WebServiceRef;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
/**
 *
 * @author eliezer
 */
@Named(value = "productoManagedBean")
@SessionScoped
public class ProductoManagedBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ws-tienda/TiendaWS.wsdl")
    private TiendaWS service;

    private Producto producto;
    private List<Producto> listaProductos;
    private ProductoCategoria categoria;
    private String selectedId;
    /**
     * Creates a new instance of ProductoManagedBean
     */
    public ProductoManagedBean() {
        this.producto = new Producto();
        this.categoria = new ProductoCategoria();
    }
    @PostConstruct
    public void init() {
        this.producto = new Producto();
        this.categoria = new ProductoCategoria();
    }
    
    public List<Producto> getListaProductos()throws Exception{
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO process result here
            this.listaProductos = port.listaProductos();
            System.out.println("Result = "+listaProductos);
            return listaProductos;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }

    }
    
    public List<ProductoCategoria> getListaCategorias(){
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO process result here
            java.util.List<com.tienda.soap.ProductoCategoria> result = port.listaCategorias();
            System.out.println("Result = "+result);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }

    }
    
    
    public void crearProducto(boolean redirect) throws Exception{
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            com.tienda.soap.Producto producto = new com.tienda.soap.Producto();
            System.out.println("Categoria: "+this.categoria);
            this.producto.setCategoriaId(categoria);
            // TODO process result here
            com.tienda.soap.Producto result = port.crearProducto(this.producto);
            System.out.println("Result = "+result);
            if(result!=null){
                this.producto = new Producto();
                Messages.addFlashGlobalInfo("Guardado correctamente");
                if(!redirect){
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("location.reload()");
                }else{
                    Faces.redirect(Faces.getRequestContextPath() + "/faces/modulos/productos/Listar.xhtml");
                }
            }else{
                Messages.addFlashGlobalError("Error al guardar");
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            Messages.addFlashGlobalError("Error al guardar :"+ex.getMessage());
        }

    }
    
     public void editarProducto(boolean redirect) throws Exception{
         
         try { // Call Web Service Operation
             com.tienda.soap.ClienteWS port = service.getClienteWSPort();
             // TODO initialize WS operation arguments here
             com.tienda.soap.Producto producto = new com.tienda.soap.Producto();
             // TODO process result here
             java.lang.String result = port.editarProducto(this.producto);
             System.out.println("Result = "+result);
             if(result.contains("Modificado correctamente")){
                Messages.addFlashGlobalInfo(result);
                this.producto=new Producto();
                if(!redirect){                    
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("location.reload()");
                }else{
                    Faces.redirect(Faces.getRequestContextPath() + "/faces/modulos/productos/Listar.xhtml");
                }
                
            }else{
                Messages.addFlashGlobalError(result);
            }
         } catch (Exception ex) {
             // TODO handle custom exceptions here
         }

     }
    public void eliminarProducto() throws Exception{ 
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            com.tienda.soap.Producto producto = new com.tienda.soap.Producto();
            // TODO process result here
            java.lang.String result = port.eliminarProducto(this.producto);
            System.out.println("Result = "+result);
            if(result.contains("Eliminado correctamente")){
                Messages.addFlashGlobalInfo(result);
            }else{
                Messages.addFlashGlobalError(result);
            }
            this.producto = new Producto();
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }

    public ProductoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ProductoCategoria categoria) {
        this.categoria = categoria;
    }
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }
    
    public void consultarPorId() throws Exception{ 
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            java.lang.Integer id = Integer.valueOf(selectedId);
            // TODO process result here
            this.producto = port.getProductoPorId(id);
            System.out.println("Result = "+producto);
        } catch (Exception ex) {
            Messages.addFlashGlobalError(ex.getMessage());
        }

    }
    
}
