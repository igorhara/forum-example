
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/do";


@Injectable()
export class AuthService{

  private userCache:UserDetail;

  constructor(private http:HttpClient){

  }

  login(user:User):Observable<UserDetail>{
      return this.http.post<UserDetail>("api/user/login",user).do(
        (u:UserDetail)=>{
          console.log(u);
          this.userCache = u;
        });

  }

  isLogged():boolean{
    return this.userCache !=null;
  }

  logout():Observable<any>{
      return this.http.post<any>("api/user/logout",null).do(()=>this.userCache=null);
  }


  get user():UserDetail{
    return this.userCache;
  }

}

export interface UserDetail{
    username:string;
}
export interface User{
  username:string;
  password:string;

}
