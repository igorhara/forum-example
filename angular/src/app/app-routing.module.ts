import {NgModule} from "@angular/core";
import { RouterModule, Routes} from "@angular/router";
import {PostListComponent} from "./post-list/post-list.component";
import {PostFormComponent} from "./post-form/post-form.component";
import {PostEditResolve} from "./post-form/post-edit.resolve";
import {LoginComponent} from "./login/login.component";
import {PostDetailComponent} from "./post-detail/post-detail.component";
import {PostViewResolve} from "./post-detail/post-view.resolve";
import {CommentEditResolve} from "./comment-form/comment-edit.resolve";
import {CommentFormComponent} from "./comment-form/comment-form.component";
import {AuthenticatedCanActivate} from "./service/authentication.guard";


const appRoutes:Routes = [
  {path:'', component:PostListComponent},
  {path:'login',component:LoginComponent},
  {path:'post/create',component:PostFormComponent,canActivate:[AuthenticatedCanActivate]},
  {path:'post/edit/:id',component:PostFormComponent,resolve:{post:PostEditResolve},canActivate:[AuthenticatedCanActivate]},
  {path:'post/view/:id',component:PostDetailComponent,resolve:{post:PostViewResolve}},
  {path:'comment/edit/:id',component:CommentFormComponent,resolve:{comment:CommentEditResolve},canActivate:[AuthenticatedCanActivate]},
  {path:'comment/create/:postId',component:CommentFormComponent,canActivate:[AuthenticatedCanActivate]}
];
@NgModule({
  imports:[
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule],
  providers:[PostEditResolve, PostViewResolve,CommentEditResolve,AuthenticatedCanActivate]
})

export class AppRoutingModule{

}
