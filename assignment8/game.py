import pygame
import time

from pygame.locals import*
from time import sleep

class Sprite():
	def __init__(self, xPos, yPos, im):
		self.x = xPos;
		self.y = yPos;
		self.image = pygame.image.load(im);
		self.images = []
	
	def collision(self, s):
		if self.x + self.w < s.x:
			return False
		if self.x > s.x + s.w:
			return False
		if self.y + self.h < s.y:
			return False
		if self.y > s.y + s.h:
			return False

		return True

	def getOffSprite(self, s):
		if self.x + self.w >= s.x and self.pX + self.w < s.x:
			self.x = s.x - self.w - 1
		elif self.x <= s.x + s.w and self.pX > s.x + s.w:
			self.x = s.x + s.w + 1
		elif self.y + self.h > s.y and self.pY < s.y:
			self.y = s.y - self.h
			self.vertVel = 0.0
			self.onSurface = True
		elif self.y < s.y + s.h and self.pY > s.y + s.h:
			self.y = s.y + s.h + 1
			self.vertVel = 0.0

class Mario(Sprite):
	def __init__(self, xPos, yPos):
		super(Mario, self).__init__(xPos, yPos, "mario1.png")
		self.pX = 0
		self.pY = 0
		self.w = 60
		self.h = 95
		self.vertVel = 0.0
		self.count = 0
		self.onSurface = False
		self.marioView = 60
		self.isAlive = True
		self.marioImageNum = 0

		self.images.append(pygame.image.load("mario1.png"))
		self.images.append(pygame.image.load("mario2.png"))
		self.images.append(pygame.image.load("mario3.png"))
		self.images.append(pygame.image.load("mario4.png"))
		self.images.append(pygame.image.load("mario5.png"))

	def update(self):
		self.vertVel += 10
		self.y += self.vertVel
		self.count += 1

		if self.y > 595 - self.h:
			self.vertVel = 0.0;
			self.y = 595 - self.h
			self.count = 0

		if self.onSurface == True:
			self.onSurface = False
			self.count = 0

		self.marioImageNum = (self.marioImageNum + 1) % 5

	def prevCord(self):
		self.pX = self.x
		self.pY = self.y

	def jump(self):
		if (self.count < 7 or self.onSurface == True):
			self.vertVel = -40

class Tube(Sprite):
	def __init__(self, xPos, yPos):
		super(Tube, self).__init__(xPos, yPos, "tube.png")
		self.w = 55
		self.h = 400
		self.isAlive = True

	def isTube(self, x, y):
		if x < self.x:
			return False
		if x > self.x + self.w:
			return False
		if y < self.y:
			return False
		if y > self.y + self.h:
			return False
		
		return True

	def update(self):
		pass

class Goomba(Sprite):
	def __init__(self, xPos, yPos):
		super().__init__(xPos, yPos, "goomba.png")
		self.w = 35
		self.h = 42
		self.vertVel = 0.0
		self.framesOnFire = 0
		self.count = 0
		self.direction = 1
		self.onFire = False
		self.isAlive = True

	def update(self):
		self.vertVel += 1.2
		self.y += self.vertVel

		if self.y > 595 - self.h:
			self.vertVel = 0.0
			self.y = 595 - self.h
			self.count = 0
		self.x += 4 * self.direction

		if self.onFire == True:
			self.framesOnFire += 1
			self.image = pygame.image.load("goombaFire.png")
		if self.framesOnFire > 10:
			self.isAlive = False

	def isGoomba(self, mouseX, mouseY):
		if mouseX < self.x:
			return False
		if mouseX > self.x + self.w:
			return False
		if mouseY < self.y:
			return False
		if mouseY > self.y + self.h:
			return False

		return True

class FireBall(Sprite):
	def __init__(self, xPos, yPos):
		super(FireBall, self).__init__(xPos, yPos, "fireball.png")
		self.w = 47
		self.h = 47
		self.vertVel = 0.0
		self.onSurface = False
		self.count = 0
		self.isAlive = True;

	def update(self):
		self.isAlive = True
		self.vertVel += 2
		self.y += self.vertVel
		self.x += 10

		if self.y > 595 - self.h:
			self.y = 596 - self.h
			self.count += 1

		if self.count > 1:
			self.vertVel = -20
			self.count = 0

class Model():
	def __init__(self):
		self.sprites = []
		self.mario = Mario(0,500)
		self.sprites.append(self.mario)
		self.sprites.append(Tube(200, 440))
		self.sprites.append(Tube(350, 500))
		self.sprites.append(Tube(470, 420))
		self.sprites.append(Tube(700, 400))
		self.sprites.append(Goomba(260, 420))
		self.sprites.append(Goomba(400, 420))
		self.sprites.append(Goomba(540, 220))

	def update(self):
		for sprite in self.sprites:
			sprite.update()

			if isinstance(sprite, Tube):
				if self.mario.collision(sprite):
					self.mario.getOffSprite(sprite)
			
				#checking for goombas
				for goomba in self.sprites:
					if isinstance(goomba, Goomba):
						if goomba.isAlive == False:
							self.sprites.remove(goomba)
						if goomba.collision(sprite):
							goomba.direction *= -1

			if isinstance(sprite, FireBall):
				for goomba in self.sprites:
					if isinstance(goomba, Goomba):
						if goomba.collision(sprite):
							goomba.onFire = True
							self.sprites.remove(sprite)
							break
						if goomba.isAlive == False:
							self.sprites.remove(goomba)

				#this is 800 becuse its thre screens x size
				if sprite.x > self.mario.x + 800:
					self.sprites.remove(sprite);

			if isinstance(sprite, Sprite):
				if sprite.isAlive == False:
					self.sprites.remove(sprite)

	def addTube(self, pos):
		found = False

		for sprite in self.sprites:
			if isinstance(sprite, Tube):
				if sprite.isTube(pos[0], pos[1]):
					self.sprites.remove(sprite)
					found = True
					break
		
		if found == False:
			self.sprites.append(Tube(pos[0], pos[1]))

	def addGoomba(self, pos):
		found = False
		
		for sprite in self.sprites:
			if isinstance(sprite, Goomba):
				if sprite.isGoomba(pos[0], pos[1]):
					self.sprites.remove(sprite)
					found = True
					break

		if found == False:
			self.sprites.append(Goomba(pos[0], pos[1]))

	def addFireBall(self):
		self.sprites.append(FireBall(self.mario.x, self.mario.y))

class View():
	def __init__(self, model):
		screen_size = (800,600)
		self.screen = pygame.display.set_mode(screen_size, 32)
		self.model = model

	def update(self):    
		self.screen.fill([0,200,100])

		for sprite in self.model.sprites:
			if isinstance(sprite, Mario):
				self.screen.blit(sprite.images[self.model.mario.marioImageNum], (self.model.mario.marioView, sprite.y))
			else:
				self.screen.blit(sprite.image, (sprite.x - self.model.mario.x + self.model.mario.marioView, sprite.y))

		pygame.display.flip()

class Controller():
	def __init__(self, model):
		self.model = model
		self.keep_going = True
		self.tubeEditor = False
		self.goombaEditor = False

	def update(self):
		self.model.mario.prevCord()

		for event in pygame.event.get():
			if event.type == QUIT:
				self.keep_going = False
			elif event.type == KEYDOWN:
				if event.key == K_ESCAPE:
					self.keep_going = False
			elif event.type == pygame.MOUSEBUTTONUP:
				if self.tubeEditor == True:
					self.model.addTube(pygame.mouse.get_pos())
				if self.goombaEditor == True:
					self.model.addGoomba(pygame.mouse.get_pos())

		keys = pygame.key.get_pressed()
		if keys[K_LEFT]:
			self.model.mario.x -= 5
		if keys[K_RIGHT]:
			self.model.mario.x += 5
		if keys[K_UP] or keys[K_SPACE]:
			self.model.mario.jump()
		if keys[K_LCTRL]:
			self.model.addFireBall()
		if keys[K_t]:
			if self.tubeEditor == True:
				print("You have disabled tube editor")
				self.tubeEditor = False
			else:
				print("you have enabled tube editor")
				self.tubeEditor = True
		if keys[K_g]:
			if self.goombaEditor == True:
				print("you have disabled goomba editor")
				self.goombaEditor = False
			else:
				print("you have enabled goomba editor")
				self.goombaEditor = True

print("Use the arrow keys to move. Press Esc to quit.")
pygame.init()
m = Model()
v = View(m)
c = Controller(m)
while c.keep_going:
	c.update()
	m.update()
	v.update()
	sleep(0.04)
print("Goodbye")
