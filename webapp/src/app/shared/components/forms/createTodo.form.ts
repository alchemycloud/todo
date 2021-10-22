import {Status} from '../../../services/backend/enums';
import {CreateTodoRequest, CreateTodoResponse, TodoApiService} from '../../../services/backend/todoApi.service';
import {StatusDropDown} from '../dropdowns/statusDropDown.dropdown';
import {
  AfterViewInit,
  Component,
  EventEmitter,
  Inject,
  Input,
  OnInit,
  Optional,
  Output,
  ViewChild
} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {debounceTime, tap} from 'rxjs/operators';

export class CreateTodoModel {
  userId: number;
  task: string;
  date: Date;
  status: Status;

  constructor(userId: number,
              task: string,
              date: Date,
              status: Status) {
    this.userId = userId;
    this.task = task;
    this.date = date;
    this.status = status;
  }

}

export class Closed {


  constructor() {

  }

}

@Component({
  selector: 'create-todo',
  templateUrl: './createTodo.form.html',
  styleUrls: ['./createTodo.form.scss']
})
export class CreateTodo implements OnInit, AfterViewInit {
  @Input() model: CreateTodoModel;
  @ViewChild(StatusDropDown)
  private readonly statusElement: StatusDropDown;
  @Output() onClosed = new EventEmitter<Closed>();
  submitDisabled = false;
  formGroup: FormGroup;
  userIdControl: FormControl;
  taskControl: FormControl;
  dateControl: FormControl;
  statusControl: FormControl;

  constructor(@Optional() private readonly dialogRef: MatDialogRef<CreateTodo>, @Inject(MAT_DIALOG_DATA) private readonly data: any,
              private readonly todoApi: TodoApiService, private readonly fb: FormBuilder) {
    this.model = data.model;
    if (data.onClosed) {
      this.onClosed.subscribe(data.onClosed);
    }
    if (this.model == null) {
      this.model = new CreateTodoModel(null, '', null, null);
    }
  }

  ngOnInit(): void {
    this.init();
    this.formGroup = this.fb.group({
      'userId': new FormControl(this.model.userId, [
        Validators.required,
        Validators.max(9223372036854775807)], []),
      'task': new FormControl(this.model.task, [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(255)], []),
      'date': new FormControl(this.model.date, [
        Validators.required], []),
      'status': new FormControl(this.model.status, [
        Validators.required], [])
    });
    this.userIdControl = this.formGroup.get('userId') as FormControl;
    this.taskControl = this.formGroup.get('task') as FormControl;
    this.dateControl = this.formGroup.get('date') as FormControl;
    this.statusControl = this.formGroup.get('status') as FormControl;

    this.formGroup.statusChanges.pipe(tap(() => {
      // fill the model with new data
      this.model.userId = this.userIdControl.value;
      this.model.task = this.taskControl.value;
      this.model.date = this.dateControl.value;
      this.model.status = this.statusControl.value;
    }), debounceTime(675)).subscribe((newStatus) => {

    });

  }

  ngAfterViewInit(): void {

  }


  onCloseDialogClick() {
    this.close();
    this.dialogRef.close();
  }

  init() {

  }

  submit() {
    this.submitDisabled = true;
    this.todoApi.createTodo(new CreateTodoRequest(this.model.userId,
      this.model.task,
      this.model.date,
      this.model.status))
      .subscribe((response: CreateTodoResponse) => {
        this.submitDisabled = false;
        this.close();
        this.dialogRef.close();
      }, (_) => {
        this.submitDisabled = false;
      });
  }

  close() {
    this.onClosed.emit(new Closed());
  }

  onSubmitClick() {
    this.submit();
  }

}

