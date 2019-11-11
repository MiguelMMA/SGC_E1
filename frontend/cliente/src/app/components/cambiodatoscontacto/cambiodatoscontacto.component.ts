import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/app/entity/Usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import Swal from 'sweetalert2';
import { UtilsService } from 'src/app/services/utils.service';


@Component({
  selector: 'app-cambiodatoscontacto',
  templateUrl: './cambiodatoscontacto.component.html',
  styleUrls: ['./cambiodatoscontacto.component.css']
})
export class CambiodatoscontactoComponent implements OnInit {

  id: string;
  usuario: Usuario = new Usuario;

  constructor(private router:Router, 
              private usuarioService: UsuarioService,
              private utilsService: UtilsService,
              private activateRoute: ActivatedRoute) { 
              
              }

  ngOnInit() {
    this.activateRoute.params.subscribe(params => {
      this.id = params['id'];
      if (this.id) {
        this.usuarioService.getUsuarioById(this.id).subscribe(
          response => {
            this.usuario = response;
            console.log(this.usuario);
            this.usuario.telefono = '';
            this.usuario.email = '';
            this.usuario.direccion = '';
            console.log(this.usuario);
          }
        )
      }
    });
  }



  modificardatoscontacto(){
    this.usuario;
    if (this.usuario.telefono.length === 0 || 
        this.usuario.email.length === 0 || 
        this.usuario.direccion.length === 0){
      Swal.fire('Error en los campos', "Todos los campos han de estar completos.", 'error');
    } else {
      if( this.utilsService.controlaremail(this.usuario.email) && 
          this.utilsService.validartelefono(this.usuario.telefono)){
        this.usuarioService.modificarDatosContactoUsuario(this.id, this.usuario).subscribe(
          response => {
            console.log(this.usuario);
            this.router.navigate(['/citas', this.id])
            Swal.fire('Datos de contacto usuario actualizados', 'Sus datos de contacto han sido actualizados con éxito', 'success');
          }
        );
      }
    }
  }

}
