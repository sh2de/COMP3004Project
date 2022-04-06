import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from '../game.service';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  player='';
  message='';
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private gameService:GameService, private route:Router,private http:HttpClient) { }

  ngOnInit(): void {
    this.message="You can join the Game"
  }

  join(){
    let url=this.apiServerUrl+"/joinGame"

    this.http.post(url,this.player).subscribe(
      (res)=>{
        console.log(res[0]);  
        this.player=res[0];  
        this.route.navigateByUrl("gameboard/"+this.player); 
      },
      (err:HttpErrorResponse)=>{
        console.log("ERROR: "+err.message);
      }
    )
    console.log("test joined player"+this.player)
    

    
  }

  

}
