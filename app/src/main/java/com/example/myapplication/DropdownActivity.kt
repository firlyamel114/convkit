package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DropdownActivity : AppCompatActivity() {

    // Cek apakah string hanya berisi 0 dan 1
    private fun isValidBinary(s: String): Boolean {
        for (c in s) {
            if (c != '0' && c != '1') return false
        }
        return true
    }

    private fun binaryToOctal(binaryString: String): String {
        if (!isValidBinary(binaryString)) {
            throw IllegalArgumentException("Input bukan bilangan biner yang valid!")
        }
        val decimal = Integer.parseInt(binaryString, 2)
        return Integer.toOctalString(decimal)
    }

    private fun octalToDecimal(octal: String): Int {
        return Integer.parseInt(octal, 8)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dropdown)

        // Toolbar - navigasi ke About
        val iconRightButton = findViewById<ImageButton>(R.id.icon_right)
        iconRightButton.setOnClickListener {
            val intent = Intent(this, ActivityAbout::class.java)
            startActivity(intent)
        }

        // Inisialisasi dropdown Dari
        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        // Inisialisasi dropdown Ke
        val autoCompleteTextView1: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView1)

        // List data dropdown
        val dropdownItems = listOf("Desimal", "Biner", "Oktal", "Heksadesimal")

        // Adapter dropdown
        val adapterDari = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dropdownItems)
        autoCompleteTextView.setAdapter(adapterDari)

        val adapterKe = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dropdownItems)
        autoCompleteTextView1.setAdapter(adapterKe)

        // Inisialisasi view
        val buttonProses = findViewById<Button>(R.id.button1)
        val buttonReset  = findViewById<Button>(R.id.button2)
        val hasil        = findViewById<TextView>(R.id.hasil)
        val inputAngka   = findViewById<TextInputEditText>(R.id.input_angka)

        // Button Reset
        buttonReset.setOnClickListener {
            hasil.text = ""
            inputAngka.setText(null)
            autoCompleteTextView.setText("", false)
            autoCompleteTextView1.setText("", false)
        }

        // Button Proses
        buttonProses.setOnClickListener {

            // Ganti .trim() dengan cara manual
            val angkaInput = inputAngka.text.toString().replace(" ", "")
            val dari       = autoCompleteTextView.text.toString().replace(" ", "")
            val ke         = autoCompleteTextView1.text.toString().replace(" ", "")

            // Validasi input kosong
            if (angkaInput.isEmpty()) {
                hasil.text = "Masukkan angka yang ingin dikonversi!"
                return@setOnClickListener
            }

            // Validasi dropdown belum dipilih
            if (dari.isEmpty() || ke.isEmpty()) {
                hasil.text = "Pilih jenis konversi dari dan ke!"
                return@setOnClickListener
            }

            // Validasi dari dan ke sama
            if (dari == ke) {
                hasil.text = "Konversi 'Dari' dan 'Ke' tidak boleh sama!"
                return@setOnClickListener
            }

            // Proses konversi
            try {
                when {
                    // Desimal ke Biner
                    dari == "Desimal" && ke == "Biner" -> {
                        val desimal = Integer.parseInt(angkaInput)
                        hasil.text  = Integer.toBinaryString(desimal)
                    }
                    // Desimal ke Oktal
                    dari == "Desimal" && ke == "Oktal" -> {
                        val desimal = Integer.parseInt(angkaInput)
                        hasil.text  = Integer.toOctalString(desimal)
                    }
                    // Desimal ke Heksadesimal
                    dari == "Desimal" && ke == "Heksadesimal" -> {
                        val desimal = Integer.parseInt(angkaInput)
                        hasil.text  = Integer.toHexString(desimal).toUpperCase()
                    }
                    // Biner ke Desimal
                    dari == "Biner" && ke == "Desimal" -> {
                        val desimal = Integer.parseInt(angkaInput, 2)
                        hasil.text  = desimal.toString()
                    }
                    // Biner ke Oktal
                    dari == "Biner" && ke == "Oktal" -> {
                        hasil.text = binaryToOctal(angkaInput)
                    }
                    // Biner ke Heksadesimal
                    dari == "Biner" && ke == "Heksadesimal" -> {
                        val desimal = Integer.parseInt(angkaInput, 2)
                        hasil.text  = Integer.toHexString(desimal).toUpperCase()
                    }
                    // Oktal ke Desimal
                    dari == "Oktal" && ke == "Desimal" -> {
                        val desimal = octalToDecimal(angkaInput)
                        hasil.text  = desimal.toString()
                    }
                    // Oktal ke Biner
                    dari == "Oktal" && ke == "Biner" -> {
                        val desimal = Integer.parseInt(angkaInput, 8)
                        hasil.text  = Integer.toBinaryString(desimal)
                    }
                    // Oktal ke Heksadesimal
                    dari == "Oktal" && ke == "Heksadesimal" -> {
                        val desimal = octalToDecimal(angkaInput)
                        hasil.text  = Integer.toHexString(desimal).toUpperCase()
                    }
                    // Heksadesimal ke Desimal
                    dari == "Heksadesimal" && ke == "Desimal" -> {
                        val desimal = Integer.parseInt(angkaInput, 16)
                        hasil.text  = desimal.toString()
                    }
                    // Heksadesimal ke Biner
                    dari == "Heksadesimal" && ke == "Biner" -> {
                        val desimal = Integer.parseInt(angkaInput, 16)
                        hasil.text  = Integer.toBinaryString(desimal)
                    }
                    // Heksadesimal ke Oktal
                    dari == "Heksadesimal" && ke == "Oktal" -> {
                        val desimal = Integer.parseInt(angkaInput, 16)
                        hasil.text  = Integer.toOctalString(desimal)
                    }
                    else -> {
                        hasil.text = "Konversi dari $dari ke $ke tidak tersedia."
                    }
                }
            } catch (e: NumberFormatException) {
                hasil.text = "Format angka tidak valid untuk konversi $dari!"
            } catch (e: IllegalArgumentException) {
                val msg = e.message
                if (msg != null) hasil.text = msg else hasil.text = "Input tidak valid!"
            }
        }
    }
}
