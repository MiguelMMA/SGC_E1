import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Cita } from 'src/app/entity/Cita';
import { CitaService } from 'src/app/services/cita.service';
import { Usuario } from 'src/app/entity/Usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import Swal from 'sweetalert2';
//import { timingSafeEqual } from 'crypto';

@Component({
  selector: 'app-pedircita',
  templateUrl: './pedircita.component.html',
  styleUrls: ['./pedircita.component.css']
})
export class PedircitaComponent implements OnInit {
  especialidadMostrar: boolean = false;
  cita: Cita = new Cita;
  usuario: Usuario = new Usuario;
  constructor(private router:Router, private citaService : CitaService, private activateRoute: ActivatedRoute, private usuarioService: UsuarioService) { }
  id:string;
  esMedicoCabecera: boolean;
  
  ngOnInit() {
    this.activateRoute.params.subscribe(params => {
      this.id = params['id'];
      if (this.id) {
        this.usuarioService.getUsuarioById(this.id).subscribe(
          response => {
            this.usuario = response;
            console.log(this.usuario);
          }
        )
      }
    });
    let x=document.getElementById("especialistas");
    let y=document.getElementById("medicos");
    x.style.display="none";
    y.style.display="none";
    this.esMedicoCabecera = true;
  }
  comprobarfecha(fecha:Date){
    let hoy=new Date();
    let v=fecha.toString()==="Invalid Date";
    let v2=fecha<hoy;
    if( v2 || v)
      return false;
    return true;
  }
  anadircita(dia: string, hora: string){
    console.log(this.especialidadMostrar);
    let horaSeparada = hora.split(':');
    let diaSeparado = dia.split('-');
    let fecha = new Date(parseInt(diaSeparado[0]),parseInt(diaSeparado[1])-1, parseInt(diaSeparado[2]),
    parseInt(horaSeparada[0]),parseInt(horaSeparada[1]) )
    if(this.comprobarfecha(fecha)){
      this.cita.dniPaciente = this.usuario.dni;
      this.cita.fecha = fecha;
      this.cita.consulta = Math.floor((Math.random() * 25) + 1);;
      if (this.esMedicoCabecera) {
        this.cita.dniMedico = this.usuario.medico;
        this.cita.especialidad = "Médico de cabecera";
      }
      if(this.cita.especialidad.length === 0 || this.cita.dniMedico.length === 0){
        Swal.fire('Error al crear cita', "Todos los campos han de estar completos.", 'error');
      } 
      else {
        console.log(this.cita);
        this.citaService.addCita(this.cita).subscribe(
          response => {
            this.router.navigate(['/citas', this.usuario._id])
            Swal.fire('Nueva cita', `Cita creada con éxito!`, 'success');
          }
        );
      }
    }
    else{
      Swal.fire('Error de fecha', "La fecha introducida no es correcta.", 'error');
    }
  }
  mostrarEspecialidad(n:number) {
    let x=document.getElementById("especialistas");
    let y=document.getElementById("medicos");
    if (n==0) {
      x.style.display="none";
      y.style.display="none";
      this.esMedicoCabecera = true;
    }
    if (n==1) {
      x.style.display="block";
      y.style.display="block";
      this.esMedicoCabecera = false;
    }
   
  }
}
