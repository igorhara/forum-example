import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable, Injector} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "./auth.service";
import "rxjs/add/observable/of";


@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor{

  constructor(private router:Router,private injector:Injector){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).catch<HttpErrorResponse>((err:HttpErrorResponse,caught)=>{
        console.log(caught);
        var auth:AuthService = this.injector.get(AuthService);
        if(err.status==403){
          alert(err.error.message);
          if(!auth.isLogged()){
             this.router.navigate(["/login"]);
          }else{
             this.router.navigate(["/"]);
          }
        }
        if(err.status==412){
          alert(err.error[0].message);
        }
        return Observable.of(err);
    });
  }

}
