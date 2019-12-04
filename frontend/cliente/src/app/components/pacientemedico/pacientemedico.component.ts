import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/entity/Usuario';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';

@Component( {
    selector: 'app-pacientemedico',
    templateUrl: './pacientemedico.component.html',
    styleUrls: ['./pacientemedico.component.css']
} )
export class PacienteMedicoComponent implements OnInit {

    msg: string = '';

    constructor( private router: Router,
        private usuarioService: UsuarioService,
        private cookieService: CookieService
    ) { }

    ngOnInit() { }

    logIn( dniP: string, dniM: string ) {
        const ajustes = {
            secure: true

        }

    /*if ( dniP.length === 0 || dniM.length === 0 ) {
       Swal.fire( 'Error en los campos', "Debes introducir el DNI del Paciente y del Médico", 'error' );
    } else {
            
        this.usuarioService.asociarPacienteMedico(dniP, dniM).subscribe(
                response => {

                    (this.respuestaPM = response)
                    console.log(this.respuestaPM);
                    if(this.respuestaPM.procesoOK){
                        
                        Swal.fire('Asociación Correcta', 'success');
                        this.router.navigate(['/panelgestor'])
                    }else{
                        
                        Swal.fire('Asociación Incorrecta', 'error');
                    
                    }
                    
                }
                
        );

        }*/

    }
}
