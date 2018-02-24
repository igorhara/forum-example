import {NgModule} from "@angular/core";
import { RouterModule, Routes} from "@angular/router";
import {PostListComponent} from "./post-list/post-list.component";
import {PostFormComponent} from "./post-form/post-form.component";
import {PostEditResolve} from "./post-form/post-edit.resolve";
import {LoginComponent} from "./login/login.component";
import {PostDetailComponent} from "./post-detail/post-detail.component";
import {PostViewResolve} from "./post-detail/post-view.resolve";


const appRoutes:Routes = [
  {path:'', component:PostListComponent},
  {path:'login',component:LoginComponent},
  {path:'post/create',component:PostFormComponent},
  {path:'post/edit/:id',component:PostFormComponent,resolve:{post:PostEditResolve}},
  {path:'post/view/:id',component:PostDetailComponent,resolve:{post:PostViewResolve}}
];
@NgModule({
  imports:[
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule],
  providers:[PostEditResolve, PostViewResolve]
})

export class AppRoutingModule{

}
