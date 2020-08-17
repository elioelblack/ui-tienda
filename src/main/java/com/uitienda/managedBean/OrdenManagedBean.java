package com.uitienda.managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import com.tienda.soap.Orden;
import com.tienda.soap.Producto;
import com.tienda.soap.OrdenDetalle;
import com.tienda.soap.Cliente;
import com.tienda.soap.ListaProductos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author eliezer
 */
@Named(value = "ordenManagedBean")
@SessionScoped
public class OrdenManagedBean implements Serializable {
    private Orden orden;
    private List<Orden> listaOrdenes;
    private OrdenDetalle ordenDetalle;
    private List<OrdenDetalle> listaorDetalles;
    private List<Cliente> listaClientes;
    private Cliente cliente;
    private Producto producto;
    private List<Producto> listaProductos;
    private Integer cantidad;
    @Inject
    ClienteManagedBean clienteManagedBean;
    @Inject
    ProductoManagedBean productoManagedBean;
    /**
     * Creates a new instance of OrdenManagedBean
     */
    public OrdenManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            this.orden = new Orden();
            this.cliente = new Cliente();
            this.listaOrdenes = new ArrayList<>();
            this.listaClientes = new ArrayList<>();
            this.producto = new Producto();
            this.listaProductos = productoManagedBean.getListaProductos();
            this.ordenDetalle = new OrdenDetalle();
            this.listaorDetalles = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(OrdenManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarItemDetalle(){
        ordenDetalle = new OrdenDetalle();
        System.out.println("Producto "+producto);
        ordenDetalle.setProdcutoId(producto);
        ordenDetalle.setCantidad(cantidad);
        listaorDetalles.add(ordenDetalle);
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public OrdenDetalle getOrdenDetalle() {
        return ordenDetalle;
    }

    public void setOrdenDetalle(OrdenDetalle ordenDetalle) {
        this.ordenDetalle = ordenDetalle;
    }

    public List<OrdenDetalle> getListaorDetalles() {
        return listaorDetalles;
    }

    public void setListaorDetalles(List<OrdenDetalle> listaorDetalles) {
        this.listaorDetalles = listaorDetalles;
    }
    
    public List<Cliente> getListaClientesSelectOne(){
        return listaClientes = clienteManagedBean.getClientList();
    }
    
    public List<Producto> getListaProductosSelectOne() throws Exception{
        return this.listaProductos = productoManagedBean.getListaProductos();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
