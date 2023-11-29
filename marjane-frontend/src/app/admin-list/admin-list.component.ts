import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { GeneralAdminDTO } from './general-admin-dto'; // Import your DTO interface

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit {
  admins: GeneralAdminDTO[];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.loadAdmins();
  }

  loadAdmins() {
    this.adminService.getAllAdmins().subscribe(data => {
      this.admins = data;
    });
  }

  deleteAdmin(id: number) {
    this.adminService.deleteAdmin(id).subscribe(() => {
      this.loadAdmins();
    });
  }
}
