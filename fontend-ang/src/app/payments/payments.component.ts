import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {StudentsService} from "../services/students.service";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements OnInit{
  public payments : any;
  public dataSource : any;
  public displayedColumns = ['id','date','amount','type',
     'status','firstName'];

  //un moyen de faire le lien entre une variable dans la partie typescript avec un component HTML
  // ViewChild => je vais chercher dans la partie HTML un objet de type MatPaginator , et après on va l'affecter à cette variable : paginator
  @ViewChild(MatPaginator) paginator! : MatPaginator;
  @ViewChild(MatSort) sort! : MatSort;
  constructor(private studentsService:StudentsService) {
  }
  ngOnInit(): void {
    this.studentsService.getAllPayments()
      .subscribe({
        next : data => {
          this.payments = data;
          this.dataSource = new MatTableDataSource(this.payments);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error : err => {
          console.log(err);
        }
      })
  }

}
