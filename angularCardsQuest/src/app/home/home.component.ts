import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from '../game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  player='';
  constructor(private gameService:GameService, private route:Router) { }

  ngOnInit(): void {
    
  }

  join(){
    
       
  }

}
