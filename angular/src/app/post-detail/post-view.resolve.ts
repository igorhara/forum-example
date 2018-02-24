import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {Post} from "../model/post.model";
import {Injectable} from "@angular/core";
import {PostService} from "../service/post.service";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
@Injectable()
export class PostViewResolve implements Resolve<Post>{


  constructor(private postService:PostService, private router:Router){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Post> | Promise<Post> | Post {
    let id:number = +route.params['id'];


    return this.postService.loadPostView(id);
  }

}
