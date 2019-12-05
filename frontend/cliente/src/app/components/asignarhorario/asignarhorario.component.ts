import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/entity/Usuario';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';

@Component( {
    selector: 'app-asignarhorario',
    templateUrl: './asignarhorario.component.html',
    styleUrls: ['./asignarhorario.component.css']
} )
export class AsignarHorarioComponent implements OnInit {

    msg: string = '';

    constructor( private router: Router,
        private usuarioService: UsuarioService,
        private cookieService: CookieService
    ) { }

    ngOnInit() { }

    asignarHorario( dniM: string, horaI: string, horaF: string ) {
        
    }
}