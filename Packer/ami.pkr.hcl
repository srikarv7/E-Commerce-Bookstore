variable "aws_region" {
  type    = string
  default = env("AWS_REGION")
}

variable "aws_vpc_id" {
  type    = string
  default = env("AWS_VPC_ID")
}

variable "aws_access_key_id" {
  type      = string
  sensitive = true
  default   = env("AWS_ACCESS_KEY_ID")
}

variable "aws_secret_key_id" {
  type      = string
  sensitive = true
  default   = env("AWS_SECRET_KEY_ID")
}

variable "aws_acct_list" {
  type = list(string)
  default = [
    env("AMI_USERS"), #12 digit alias
  ]
}

variable "source_ami" {
  type    = string
  default = env("AWS_SOURCE_AMI") # Ubuntu 22.04 LTS
}

variable "ssh_username" {
  type    = string
  default = "ubuntu"
}

variable "subnet_id" {
  type    = string
  default = env("AWS_DEFAULT_SUBNET")
}

# https://www.packer.io/plugins/builders/amazon/ebs
source "amazon-ebs" "my-ami" {
  access_key      = "${var.aws_access_key_id}"
  secret_key      = "${var.aws_secret_key_id}"
  vpc_id          = "${var.aws_vpc_id}"
  region          = "${var.aws_region}"
  ami_users       = "${var.aws_acct_list}"
  ami_name        = "csye6225_MySQL${formatdate("YYYY_MM_DD_hh_mm_ss", timestamp())}"
  ami_description = "AMI for CSYE 6225"
  ami_regions = [
    "us-east-1",
  ]

  aws_polling {
    delay_seconds = 120
    max_attempts  = 50
  }


  instance_type = "t2.micro"
  source_ami    = "${var.source_ami}"
  ssh_username  = "${var.ssh_username}"
  subnet_id     = "${var.subnet_id}"

  launch_block_device_mappings {
    delete_on_termination = true
    device_name           = "/dev/sda1"
    volume_size           = 8
    volume_type           = "gp2"
  }
}

build {
  sources = ["source.amazon-ebs.my-ami"]

  provisioner "file"{
    source = "../application-0.0.1-SNAPSHOT.jar"
    destination = "/home/ubuntu/"
  }
  #provisioner "file"{
  #  source = "./application-0.0.1-SNAPSHOTPostgres.jar"
  #  destination = "/home/ubuntu/"
  #}

  provisioner "file"{
    source = "./application.service"
    destination = "/tmp/application.service"
  }

  provisioner "file"{
    source = "./postgres.service"
    destination = "/tmp/postgres.service"
  }

  provisioner "shell" {
    environment_vars = [
      "DEBIAN_FRONTEND=noninteractive",
      "CHECKPOINT_DISABLE=1"
    ]
    script = "./app.sh"
    // inline = [
    //   "sudo apt-get update",
    //   "sudo apt-get upgrade -y",
    //   "sudo apt-get install nginx -y",
    //   "sudo apt-get clean",

    //   ## Install Open Jdk 8 java(Do not use this)
    //   #"sudo add-apt-repository ppa:openjdk-r/ppa",
    //   #"sudo apt-get update",
    //   #"sudo apt-get install -y openjdk-8-jdk",
    //   #"java -version",

    //   ####sudo apt-get install openjdk-11-jdk
    //   #Provision the file here using file provisioner
    //   ####java -jar application-0.0.1-SNAPSHOT.jar


      
    //   ## Install PostgreSQL
    //   #"sudo apt install postgresql postgresql-contrib"
    //   #"sudo systemctl start postgresql.service"


    // ]
  }
}



      ## Install tomcat 8
      #"sudo apt-get install -y tomcat8

      ### wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.68/bin/apache-tomcat-9.0.68.tar.gz
      ### tar -zvxf apache-tomcat-9.0.68.tar.gz
      ### cd apache-tomcat-9.0.68/
      ### cd bin/
      ### ./startup.sh
