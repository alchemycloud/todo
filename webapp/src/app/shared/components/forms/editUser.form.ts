import {UserRole} from '../../../services/backend/enums';
import {
  ReadUserRequest,
  ReadUserResponse,
  UpdateUserRequest,
  UpdateUserResponse,
  UserApiService
} from '../../../services/backend/userApi.service';
import {UserRoleDropDown} from '../dropdowns/userRoleDropDown.dropdown';
import {
  AfterViewInit,
  Component,
  EventEmitter,
  Inject,
  Input,
  OnChanges,
  OnInit,
  Optional,
  Output,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {debounceTime, tap} from 'rxjs/operators';

export class EditUserModel {
  id: number;
  firstName: string;
  lastName: string;
  role: UserRole;
  username: string;
  passwordHash: string;

  constructor(id: number,
              firstName: string,
              lastName: string,
              role: UserRole,
              username: string,
              passwordHash: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.username = username;
    this.passwordHash = passwordHash;
  }

}

export class Closed {


  constructor() {

  }

}

@Component({
  selector: 'edit-user',
  templateUrl: './editUser.form.html',
  styleUrls: ['./editUser.form.scss']
})
export class EditUser implements OnChanges, OnInit, AfterViewInit {
  @Input() id: number = null;
  @ViewChild(UserRoleDropDown)
  private readonly roleElement: UserRoleDropDown;
  model: EditUserModel = new EditUserModel(null, '', '', null, '', '');
  @Output() onClosed = new EventEmitter<Closed>();
  submitDisabled = false;
  formGroup: FormGroup;
  firstNameControl: FormControl;
  lastNameControl: FormControl;
  roleControl: FormControl;
  usernameControl: FormControl;
  passwordHashControl: FormControl;

  constructor(@Optional() private readonly dialogRef: MatDialogRef<EditUser>, @Inject(MAT_DIALOG_DATA) private readonly data: any,
              private readonly userApi: UserApiService, private readonly fb: FormBuilder) {
    this.id = data.id;
    if (data.onClosed) {
      this.onClosed.subscribe(data.onClosed);
    }
  }

  ngOnInit(): void {
    this.init();
    this.formGroup = this.fb.group({
      firstName: new FormControl(this.model.firstName, [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(40)], []),
      lastName: new FormControl(this.model.lastName, [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(60)], []),
      role: new FormControl(this.model.role, [
        Validators.required], []),
      username: new FormControl(this.model.username, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(128)], []),
      passwordHash: new FormControl(this.model.passwordHash, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(128)], [])
    });
    this.firstNameControl = this.formGroup.get('firstName') as FormControl;
    this.lastNameControl = this.formGroup.get('lastName') as FormControl;
    this.roleControl = this.formGroup.get('role') as FormControl;
    this.usernameControl = this.formGroup.get('username') as FormControl;
    this.passwordHashControl = this.formGroup.get('passwordHash') as FormControl;

    this.formGroup.statusChanges.pipe(tap(() => {
      // fill the model with new data
      this.model.firstName = this.firstNameControl.value;
      this.model.lastName = this.lastNameControl.value;
      this.model.role = this.roleControl.value;
      this.model.username = this.usernameControl.value;
      this.model.passwordHash = this.passwordHashControl.value;
    }), debounceTime(675)).subscribe((newStatus) => {

    });

  }

  ngAfterViewInit(): void {

  }


  ngOnChanges(changes: SimpleChanges): void {
    if (changes.id && !changes.id.firstChange && changes.id.previousValue !== changes.id.currentValue) {
      this.reload(changes.id.currentValue);
    }
  }

  onCloseDialogClick() {
    this.close();
    this.dialogRef.close();
  }

  init() {
    this.load();
  }

  submit() {
    this.submitDisabled = true;
    this.userApi.updateUser(new UpdateUserRequest(this.model.id,
      this.model.firstName,
      this.model.lastName,
      this.model.role,
      this.model.username,
      this.model.passwordHash))
      .subscribe((response: UpdateUserResponse) => {
        this.submitDisabled = false;
        this.close();
        this.dialogRef.close();
      }, (_) => {
        this.submitDisabled = false;
      });
  }

  load() {
    this.userApi.readUser(new ReadUserRequest(this.id))
      .subscribe((response: ReadUserResponse) => {
        this.model.id = response.id;
        this.model.firstName = response.firstName;
        this.model.lastName = response.lastName;
        this.model.role = response.role;
        this.model.username = response.username;
        this.model.passwordHash = response.passwordHash;
        this.formGroup.patchValue(response, {emitEvent: false});
      }, (_) => {
      });
  }

  reload(id: number) {
    this.id = id;
    this.load();
  }

  close() {
    this.onClosed.emit(new Closed());
  }

  onSubmitClick() {
    this.submit();
  }

}

