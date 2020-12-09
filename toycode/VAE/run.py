import torch
from torch import optim
from models.dip_vae import DIPVAE
from utils.dataload import VAEWAVE
from torch.utils.data import Dataset, DataLoader
from torchvision import transforms, utils
from matplotlib import pyplot as plt

device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
model = DIPVAE(in_channels=3, latent_dim=2048, hidden_dim=None, lambda_diag=0.05, lambda_offdiag=0.1)
model = model.to(device=device)

transform = transforms.Compose([transforms.Resize((256,256)),transforms.ToTensor()])
train_loader = DataLoader(VAEWAVE('./mel-image',
                                transform=transform,
                                target_transform=None),
                            shuffle=True,
                            batch_size=64,
                            num_workers=8
                        )
optimizer = optim.Adam(model.parameters(),
                        lr=0.001,
                        weight_decay=0.0)
scheduler = optim.lr_scheduler.ExponentialLR(optimizer, gamma = 0.97)

for epoch in range(5):
    for idx, batch in enumerate(train_loader):
        optimizer.step()
        scheduler.step()
        real_img, labels = batch
        real_img = real_img.to(device)
        results = model.forward(real_img)
        train_loss = model.loss_function(*results)
        print(epoch,idx,{key: val.item() for key, val in train_loss.items()})
        train_loss['loss'].backward()
    torch.save({
            'epoch': epoch,
            'model_state_dict': model.state_dict(),
            'optimizer_state_dict': optimizer.state_dict(),
            'loss': train_loss,
            }, 'dip_vae.pth')