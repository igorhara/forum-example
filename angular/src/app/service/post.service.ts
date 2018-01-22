import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Post} from "../model/post.model";
@Injectable()
export class PostService{

  constructor(private http:HttpClient){

  }

  getAllPosts():Observable<Post[]>{
    return this.http.get<Post[]>('api/post/all');
  }

}