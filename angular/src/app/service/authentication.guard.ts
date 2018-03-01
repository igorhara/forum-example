

import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";

@Injectable()
export class AuthenticatedCanActivate implements CanActivate{

    constructor(private auth:AuthService){

    }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean{
      return this.auth.checkIfLoggedInServer().map<boolean>(u=>u.username!=null);
  }


}
