/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uitienda.managedBean;

import com.tienda.soap.Cliente;
import com.tienda.soap.TiendaWS;
import com.uitienda.model.ClienteModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.WebServiceRef;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;


/**
 *
 * @author LENOVO
 */
@Named(value = "clienteManagedBean")
@SessionScoped
public class ClienteManagedBean implements Serializable {

    private List<ClienteModel> listaClientes= new ArrayList<>();
    private Cliente cliente = new Cliente();
    private String selectedId;
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ws-tienda/TiendaWS.wsdl")
    private TiendaWS service;

    /**
     * Creates a new instance of ClienteManagedBean
     */
    public ClienteManagedBean() {
        this.cliente = new Cliente();
    }
    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
    }
    public List<com.tienda.soap.Cliente> getClientList(){        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO process result here
            java.util.List<com.tienda.soap.Cliente> result = port.listaClientes();
            System.out.println("Result = "+result);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            return null;
        }
    }
    
    public void crearCliente(boolean redirect) throws Exception{
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String primerNombre = this.cliente.getPrimerNombre();
            java.lang.String segundoNombre = this.cliente.getSegundoNombre();
            java.lang.String primerApellido = this.cliente.getPrimerApellido();
            java.lang.String segundoApellido = this.cliente.getSegundoApellido();
            java.lang.String pDui = this.cliente.getDuiCliente();
            java.lang.String pNit = this.cliente.getNitCliente();
            java.lang.Integer flagEstado = Integer.valueOf(this.cliente.getFlagEstado());
            //System.out.println("primer nombre: "+this.cliente.getPrimerNombre());
            // TODO process result here
            com.tienda.soap.Cliente result = port.nuevoCliente(primerNombre, segundoNombre, primerApellido, segundoApellido, pDui, pNit, flagEstado);
            System.out.println("Result = "+result);
            if(result!=null){                
                this.cliente = new Cliente();
                Messages.addFlashGlobalInfo("Guardado correctamente");
                if(!redirect){                    
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("location.reload()");
                }else{
                    Faces.redirect(Faces.getRequestContextPath() + "/faces/modulos/clientes/Listar.xhtml");
                }
                
                
            }else{
                Messages.addFlashGlobalError("Error al guardar");
            }
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            Messages.addFlashGlobalError(ex.getMessage());
        }

    }
    public void editarCliente(boolean redirect){
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            com.tienda.soap.Cliente cliente = new com.tienda.soap.Cliente();
            cliente = this.cliente;
            // TODO process result here
            System.out.println("Nombre "+this.cliente.getPrimerNombre());
            java.lang.String result = port.editarCliente(this.cliente);
            System.out.println("Result = "+result);
            if(result.contains("Modificado correctamente")){
                Messages.addFlashGlobalInfo(result);
                this.cliente=new Cliente();
                if(!redirect){                    
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("location.reload()");
                }else{
                    Faces.redirect(Faces.getRequestContextPath() + "/faces/modulos/clientes/Listar.xhtml");
                }
                
            }else{
                Messages.addFlashGlobalError(result);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
    
    public void eliminar() throws Exception{
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            com.tienda.soap.Cliente cliente = new com.tienda.soap.Cliente();
            // TODO process result here
            java.lang.String result = port.eliminarCliente(this.cliente);
            System.out.println("Result = "+result);
            if(result.contains("Eliminado correctamente")){
                Messages.addFlashGlobalInfo(result);
            }else{
                Messages.addFlashGlobalError(result);
            }
            this.cliente = new Cliente();
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

       
    }
    
    public void consultarPorId() {        
        //this.selected = this.intInteresService.consultarPorIdInteres(Integer.valueOf(selectedId));
        
        try { // Call Web Service Operation
            com.tienda.soap.ClienteWS port = service.getClienteWSPort();
            // TODO initialize WS operation arguments here
            java.lang.Integer id = Integer.valueOf(this.selectedId);
            // TODO process result here
            this.cliente = port.getClientePorId(id);
            System.out.println("Result = "+cliente);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }

    public List<ClienteModel> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<ClienteModel> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }
    
    
    
}
