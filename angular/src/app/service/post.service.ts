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

  createPost(post:Post):Observable<Post>{
    return this.http.post<Post>("api/post",post);
  }

  loadPost(id: number):Observable<Post> {
     return this.http.get<Post>('api/post/edit/'+id);
  }
  loadPostView(id: number):Observable<Post> {
     return this.http.get<Post>('api/post/view/'+id);
  }

  updatePost(post:Post):Observable<Post>{
    return this.http.put<Post>("api/post",post);
  }
}
