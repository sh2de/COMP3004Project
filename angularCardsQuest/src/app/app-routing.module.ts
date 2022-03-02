import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameboardComponent } from './gameboard/gameboard.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'gameboard', component:GameboardComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
