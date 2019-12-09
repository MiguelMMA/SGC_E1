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
  
    asignarPacMed( dniP: string, dniM: string ) {        
        this.usuarioService.asociarPacienteMedico(dniP, dniM).subscribe(
                response => {                        
                        Swal.fire('Asociación Correcta', 'success');
                        this.router.navigate(['/panelgestor']);
                    
                    }
        )
                    
    }
}