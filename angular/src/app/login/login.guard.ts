import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class LoginGuard implements CanActivate{
   constructor(private auth:AuthService){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    route.toString()
    return !this.auth.isLogged();
  }



}
