
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
          this.userCache = u;
        });

  }

  checkLogin(){
    this.http.get<UserDetail>("api/user/login").subscribe((u:UserDetail)=>{
      this.userCache = u;
    })
  }

  isLogged():boolean{
    return this.userCache !=null;
  }

  logout(){
    this.http.get<any>("api/user/logout").subscribe(()=>this.userCache=null);
  }


  get user():UserDetail{
    return this.userCache;
  }

  isCurrentUser(login:string):boolean{
    if(!this.isLogged()){
      return false;
    }
    return this.user.username===login;
  }

}

export interface UserDetail{
    username:string;
}
export interface User{
  username:string;
  password:string;

}
