import {Component, OnInit} from '@angular/core';
import {StudentsService} from "../services/students.service";
import {ActivatedRoute} from "@angular/router";
import {coreHasSymbol} from "@angular/compiler-cli/src/ngtsc/core/src/core_version";

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrl: './payment-details.component.css'
})
export class PaymentDetailsComponent implements OnInit{
  paymentId! : number;
  pdfFileUrl : any;
  constructor(private studentsService:StudentsService,
              private route:ActivatedRoute) {

  }

  ngOnInit(): void {
    this.paymentId = this.route.snapshot.params['id'];
    this.studentsService.getPaymenyDetails(this.paymentId).subscribe(
      {
        next : value => {
          let blob = new Blob([value],{type:'application/pdf'});
          this.pdfFileUrl = window.URL.createObjectURL(blob);
        },
        error : err => {
          console.log(err);
        }
      }
    )
  }

  afterLoadComplete(event: any) {

  }
}
