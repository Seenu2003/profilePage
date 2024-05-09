package com.seenu.profilepage2

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.seenu.profilepage2.ui.theme.ProfilePage2Theme
import java.io.IOException
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfilePage2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var number by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var openAppointments by rememberSaveable {
        mutableStateOf(false)
    }
    var openWorkshops by rememberSaveable {
        mutableStateOf(false)
    }
    val notifiy = rememberSaveable{mutableStateOf("")}
    if(notifiy.value.isNotEmpty()){
        Toast.makeText(LocalContext.current, notifiy.value, Toast.LENGTH_LONG).show()
        notifiy.value=""
    }
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp),
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text="Cancel" ,modifier = Modifier.clickable{})
            Text(text="Save" ,modifier = Modifier.clickable{notifiy.value="SAVED"})
        }
        ProfileImage()
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="User Name", modifier=Modifier.width(100.dp))
            TextField(value = username, onValueChange = {username = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="Name", modifier=Modifier.width(100.dp))
            TextField(value = name, onValueChange = {name = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="Phone Number", modifier=Modifier.width(100.dp))
            TextField(value = number, onValueChange = {number = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="Email", modifier=Modifier.width(100.dp))
            TextField(value = email, onValueChange = {email = it},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { openWorkshops = true },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Workshops", textAlign = TextAlign.Center)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null )
        }
        DropdownMenu(expanded = openWorkshops, onDismissRequest = { openWorkshops=false }, modifier = Modifier.fillMaxWidth()) {
            DropdownMenuItem(text = { Text(text = "Workshop 1", textAlign = TextAlign.Center) }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Workshop 2" , textAlign = TextAlign.Center) }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Workshop 3", textAlign = TextAlign.Center) }, onClick = { /*TODO*/ })
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { openAppointments = true },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Appointments", textAlign = TextAlign.Center)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null )
        }
        DropdownMenu(expanded = openAppointments, onDismissRequest = { openAppointments=false }, modifier = Modifier.fillMaxWidth()) {
            DropdownMenuItem(text = { Text(text = "Appointment 1", textAlign = TextAlign.Center) }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Appointment 2" , textAlign = TextAlign.Center) }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Appointment 3", textAlign = TextAlign.Center) }, onClick = { /*TODO*/ })
        }
        ImagePickerButton()

    }
}
@Composable
fun ProfileImage(){
val imageUri= rememberSaveable{ mutableStateOf("") }
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty()){
            R.drawable.ic_user
        }
        else{
            imageUri.value
        }
    )
    val laucher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        uri.let{imageUri.value=it.toString()}
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Card(shape= CircleShape,
           modifier = Modifier
               .padding(8.dp)
               .size(100.dp),
           border = BorderStroke(4.dp, Color(251, 133, 0))
       ) {
           Image(painter = painter, contentDescription = null, modifier = Modifier
               .wrapContentSize()
               .clickable { laucher.launch("image/*") },
               contentScale = ContentScale.Crop)

       }
        Text(text = "Change Profile Picture")
    }
}

@Composable
fun ImagePickerButton() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            selectedImageUri = uri
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { getContent.launch("image/*") },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(251, 134, 0),
                containerColor = Color.Transparent
            )) {
            Icon(Icons.Default.AddCircle, contentDescription = null)
            Text(text = "Add Prescreptions", fontSize = 20.sp )
        }
    }
}